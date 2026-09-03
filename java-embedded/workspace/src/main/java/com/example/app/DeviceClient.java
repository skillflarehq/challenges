package com.example.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public final class DeviceClient implements Runnable {
  static final String SENSOR_ID = "TEMP1";
  static final String ACTUATOR_ID = "VENT1";
  static final double THRESHOLD = 80.0;
  static final int DEFAULT_EMU_PORT = 9100;

  // Implement the SIL client loop. Do not change the public HTTP paths (see Handlers).
  //
  // Wire protocol (one UTF-8 line per frame, \n-terminated). Use Protocol helpers:
  //   emulator -> you  SENS,<sensor_id>,<value>,<checksum>
  //   you -> emulator  CMD,<actuator_id>,<ON|OFF>,<checksum>
  // Checksum is 8-bit XOR of the payload before the last comma, two uppercase hex digits
  // (Protocol.checksumHex / parseLine / formatCmd).
  //
  // On each valid SENS frame (parseLine != null AND checksumValid):
  //   - update Status.sensor and Status.value
  //   - if sensor id equals SENSOR_ID and numeric value is strictly greater than THRESHOLD,
  //     send Protocol.formatCmd(ACTUATOR_ID, "ON") and set Status.command to "ON"
  // Drop malformed lines and frames whose checksum is invalid — do not treat them as trips.
  //
  // Connect to 127.0.0.1 at DEFAULT_EMU_PORT (or EMU_PORT from the environment).
  // Set Status.linked = true once the TCP connection is up.

  @Override
  public void run() {
    int port = DEFAULT_EMU_PORT;
    String envPort = System.getenv("EMU_PORT");
    if (envPort != null && !envPort.isBlank()) {
      port = Integer.parseInt(envPort);
    }

    Socket socket;
    try {
      socket = connectWithRetry(port);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    if (socket == null) {
      System.err.println("Could not connect to emulator on 127.0.0.1:" + port);
      return;
    }
    try (socket) {
      BufferedReader in =
          new BufferedReader(
              new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
      PrintWriter out =
          new PrintWriter(
              new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
      // Implement the client loop using `in` and `out`.
      // Keep the connection open until the emulator closes it (readLine returns null).
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static Socket connectWithRetry(int port) throws IOException {
    IOException last = null;
    for (int i = 0; i < 30; i++) {
      Socket socket = new Socket();
      try {
        socket.connect(new InetSocketAddress("127.0.0.1", port), 400);
        return socket;
      } catch (IOException e) {
        last = e;
        try {
          socket.close();
        } catch (IOException ignored) {
          // retry
        }
        try {
          Thread.sleep(400);
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt();
          return null;
        }
      }
    }
    if (last != null) {
      System.err.println("Emulator connect retries exhausted: " + last.getMessage());
    }
    return null;
  }
}
