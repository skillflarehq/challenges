package com.example.app;

/**
 * Shared SIL status for {@code GET /status}. {@link DeviceClient} should update these
 * fields; {@link Handlers} should read them. All fields are {@code volatile} for the
 * HTTP thread vs the TCP client thread.
 */
public final class Status {
  private Status() {}

  public static volatile boolean linked = false;
  public static volatile String sensor = "";
  public static volatile Double value = null;
  public static volatile String command = "none";
}
