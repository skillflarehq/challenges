#include "httplib.h"
#include "handlers.hpp"

#include <cstdlib>
#include <iostream>
#include <string>

int main() {
  const char *env_port = std::getenv("PORT");
  const int port = env_port ? std::atoi(env_port) : 3000;
  const char *env_host = std::getenv("HOST");
  const std::string host =
      (env_host && env_host[0] != '\0') ? env_host : "0.0.0.0";

  httplib::Server svr;
  svr.Get("/", handle_root);
  svr.Get("/health", handle_health);
  svr.Get("/summary", handle_summary);

  std::cout << "Listening on " << host << ":" << port << std::endl;
  if (!svr.listen(host, port)) {
    std::cerr << "Failed to listen on " << host << ":" << port << std::endl;
    return 1;
  }
  return 0;
}
