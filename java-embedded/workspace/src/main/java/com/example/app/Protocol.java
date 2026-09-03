package com.example.app;

import java.nio.charset.StandardCharsets;

/**
 * ASCII SIL frames, one UTF-8 line each, {@code \n}-terminated on the wire.
 *
 * <pre>
 *   SENS,&lt;sensor_id&gt;,&lt;value&gt;,&lt;checksum&gt;
 *   CMD,&lt;actuator_id&gt;,&lt;ON|OFF&gt;,&lt;checksum&gt;
 * </pre>
 *
 * Checksum is the 8-bit XOR of the payload bytes <em>before</em> the last comma,
 * printed as two uppercase hex digits. Example: {@code SENS,TEMP1,72.5,28}.
 */
public final class Protocol {
  private Protocol() {}

  public record Frame(
      String type, String id, String value, String checksum, boolean checksumValid) {}

  public static String checksumHex(String payload) {
    int xor = 0;
    for (byte b : payload.getBytes(StandardCharsets.UTF_8)) {
      xor ^= (b & 0xff);
    }
    return String.format("%02X", xor);
  }

  public static String formatSens(String sensorId, String value) {
    String payload = "SENS," + sensorId + "," + value;
    return payload + "," + checksumHex(payload);
  }

  public static String formatCmd(String actuatorId, String state) {
    String payload = "CMD," + actuatorId + "," + state;
    return payload + "," + checksumHex(payload);
  }

  /**
   * Parse one frame line (with or without trailing newline). Returns {@code null} if
   * the line does not have the four-field {@code TYPE,id,value,checksum} shape.
   */
  public static Frame parseLine(String line) {
    if (line == null) {
      return null;
    }
    String trimmed = line.strip();
    int lastComma = trimmed.lastIndexOf(',');
    if (lastComma <= 0 || lastComma == trimmed.length() - 1) {
      return null;
    }
    String payload = trimmed.substring(0, lastComma);
    String checksum = trimmed.substring(lastComma + 1);
    String[] parts = payload.split(",", -1);
    if (parts.length != 3 || parts[0].isEmpty() || parts[1].isEmpty()) {
      return null;
    }
    boolean valid = checksumHex(payload).equalsIgnoreCase(checksum);
    return new Frame(parts[0], parts[1], parts[2], checksum, valid);
  }
}
