package com.example.emu;

import com.example.app.Protocol;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

/**
 * SIL board emulator. Do not modify this class — treat it as the hardware.
 *
 * Sequence after a client connects: two nominal SENS frames, one high-value frame
 * with a bad checksum (must be ignored), then a valid trip SENS. Prints
 * {@code SCENARIO PASS} when it receives a valid {@code CMD,<actuator>,ON,<checksum>}
 * after the trip, otherwise {@code SCENARIO FAIL}.
 */
public final class BoardEmulator {
  static final String SENSOR_ID = "TEMP1";
  static final String ACTUATOR_ID = "VENT1";
  static final double THRESHOLD = 80.0;
  static final String NOMINAL_1 = "72.5";
  static final String NOMINAL_2 = "75.0";
  static final String TRIP_VALUE = "85.0";
  static final int DEFAULT_EMU_PORT = 9100;

  public static void main(String[] args) throws IOException {
    int port = DEFAULT_EMU_PORT;
    String envPort = System.getenv("EMU_PORT");
    if (envPort != null && !envPort.isBlank()) {
      port = Integer.parseInt(envPort);
    }

    try (ServerSocket server = new ServerSocket(port, 1, InetAddress.getLoopbackAddress())) {
      System.out.println("Emulator listening on 127.0.0.1:" + port);
      System.out.println(
          "Scenario: "
              + SENSOR_ID
              + " trip > "
              + THRESHOLD
              + " -> CMD "
              + ACTUATOR_ID
              + " ON");
      try (Socket client = server.accept()) {
        System.out.println("Client connected from " + client.getRemoteSocketAddress());
        runScenario(client);
      }
    }
  }

  static void runScenario(Socket client) throws IOException {
    client.setTcpNoDelay(true);
    BufferedReader in =
        new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
    PrintWriter out =
        new PrintWriter(new OutputStreamWriter(client.getOutputStream(), StandardCharsets.UTF_8), true);

    try {
      Thread.sleep(400);
    } catch (InterruptedException ie) {
      Thread.currentThread().interrupt();
    }

    client.setSoTimeout(200);
    boolean earlyCommand = false;
    earlyCommand |= sendSens(out, in, Protocol.formatSens(SENSOR_ID, NOMINAL_1));
    earlyCommand |= sendSens(out, in, Protocol.formatSens(SENSOR_ID, NOMINAL_2));
    client.setSoTimeout(1000);
    earlyCommand |= sendSens(out, in, badChecksumSens(SENSOR_ID, TRIP_VALUE));

    if (earlyCommand) {
      System.out.println("SCENARIO FAIL");
      System.out.println("Command received before a valid trip SENS (bad checksum or nominal).");
      return;
    }

    String trip = Protocol.formatSens(SENSOR_ID, TRIP_VALUE);
    System.out.println("Sent " + trip);
    out.println(trip);

    client.setSoTimeout(15000);
    boolean pass = waitForTripCommand(in);
    if (pass) {
      System.out.println("SCENARIO PASS");
    } else {
      System.out.println("SCENARIO FAIL");
      System.out.println("No valid CMD," + ACTUATOR_ID + ",ON,<checksum> after the trip SENS.");
    }
  }

  static boolean sendSens(PrintWriter out, BufferedReader in, String frame) throws IOException {
    System.out.println("Sent " + frame);
    out.println(frame);
    return pollEarlyCommand(in);
  }

  static String badChecksumSens(String sensorId, String value) {
    String payload = "SENS," + sensorId + "," + value;
    String good = Protocol.checksumHex(payload);
    String bad = "00".equals(good) ? "FF" : "00";
    return payload + "," + bad;
  }

  static boolean pollEarlyCommand(BufferedReader in) throws IOException {
    try {
      String line = in.readLine();
      return isTripCommand(line);
    } catch (SocketTimeoutException timeout) {
      return false;
    }
  }

  static boolean waitForTripCommand(BufferedReader in) throws IOException {
    long deadline = System.currentTimeMillis() + 15000;
    while (System.currentTimeMillis() < deadline) {
      try {
        String line = in.readLine();
        if (line == null) {
          return false;
        }
        System.out.println("Recv " + line);
        if (isTripCommand(line)) {
          return true;
        }
      } catch (SocketTimeoutException timeout) {
        return false;
      }
    }
    return false;
  }

  static boolean isTripCommand(String line) {
    Protocol.Frame frame = Protocol.parseLine(line);
    if (frame == null || !frame.checksumValid()) {
      return false;
    }
    return "CMD".equals(frame.type())
        && ACTUATOR_ID.equals(frame.id())
        && "ON".equalsIgnoreCase(frame.value());
  }

  private BoardEmulator() {}
}
