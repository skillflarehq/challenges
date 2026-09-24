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
  //                 {"ok":true,"message":"Hello from Java"}
  // GET /health  -> HTTP 200, Content-Type application/json
  //                 {"status":"ok"}
  // GET /summary -> HTTP 200, Content-Type application/json
  //                 {"group":"<name>","total":<number>,"skipped":<int>}
  //
  // For /summary, read data/items.csv from the process working directory
  // (workspace root). Skip the header row. Group by `category` and sum
  // numeric `amount`. Skip rows with an empty category or a non-numeric
  // amount. The winner is the category with the highest total; ties go to
  // the lexicographically smallest category name.

  public static void root(HttpExchange exchange) throws IOException {
    send(exchange, 501, "{\"error\":\"not implemented\"}");
  }

  public static void health(HttpExchange exchange) throws IOException {
    send(exchange, 501, "{\"error\":\"not implemented\"}");
  }

  public static void summary(HttpExchange exchange) throws IOException {
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
