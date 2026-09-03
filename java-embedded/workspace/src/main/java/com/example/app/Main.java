package com.example.app;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public final class Main {
  public static void main(String[] args) throws IOException {
    int port = 3000;
    String envPort = System.getenv("PORT");
    if (envPort != null && !envPort.isBlank()) {
      port = Integer.parseInt(envPort);
    }
    String host = System.getenv("HOST");
    if (host == null || host.isBlank()) {
      host = "0.0.0.0";
    }

    Thread client = new Thread(new DeviceClient(), "device-client");
    client.setDaemon(false);
    client.start();

    HttpServer server = HttpServer.create(new InetSocketAddress(host, port), 0);
    server.createContext("/health", Handlers::health);
    server.createContext("/status", Handlers::status);
    server.createContext("/", Handlers::root);
    server.setExecutor(null);
    System.out.println("Listening on " + host + ":" + port);
    server.start();
  }

  private Main() {}
}
