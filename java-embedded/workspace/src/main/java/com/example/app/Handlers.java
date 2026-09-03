package com.example.app;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public final class Handlers {
  private Handlers() {}

  // Implement these handlers. Do not change the public paths.
  //
  // GET /        -> HTTP 200, Content-Type application/json
  //                 {"ok":true,"message":"Battery thermal controller ready"}
  // GET /health  -> HTTP 200, Content-Type application/json
  //                 {"status":"ok"}
  // GET /status  -> HTTP 200, Content-Type application/json
  //                 {"sensor":"<id>","value":<number|null>,"command":"ON"|"OFF"|"none","linked":true|false}
  //
  // Read live values from Status (updated by DeviceClient):
  //   sensor  = last valid SENS id (empty string if none yet)
  //   value   = last valid SENS numeric value, or JSON null if none yet
  //   command = last CMD state sent ("ON", "OFF", or "none")
  //   linked  = true after the TCP client has connected to the emulator

  public static void root(HttpExchange exchange) throws IOException {
    send(exchange, 501, "{\"error\":\"not implemented\"}");
  }

  public static void health(HttpExchange exchange) throws IOException {
    send(exchange, 501, "{\"error\":\"not implemented\"}");
  }

  public static void status(HttpExchange exchange) throws IOException {
    send(exchange, 501, "{\"error\":\"not implemented\"}");
  }

  static void send(HttpExchange exchange, int status, String body) throws IOException {
    byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
    exchange.getResponseHeaders().set("Content-Type", "application/json");
    exchange.sendResponseHeaders(status, bytes.length);
    try (OutputStream out = exchange.getResponseBody()) {
      out.write(bytes);
    }
  }
}
