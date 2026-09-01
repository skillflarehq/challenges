#include "handlers.hpp"

// Implement these handlers. Do not change the public paths.
//
// GET /        -> HTTP 200, Content-Type application/json
//                 {"ok":true,"message":"Hello from C++"}
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

void handle_root(const httplib::Request &, httplib::Response &res) {
  res.status = 501;
  res.set_content("{\"error\":\"not implemented\"}", "application/json");
}

void handle_health(const httplib::Request &, httplib::Response &res) {
  res.status = 501;
  res.set_content("{\"error\":\"not implemented\"}", "application/json");
}

void handle_summary(const httplib::Request &, httplib::Response &res) {
  res.status = 501;
  res.set_content("{\"error\":\"not implemented\"}", "application/json");
}
