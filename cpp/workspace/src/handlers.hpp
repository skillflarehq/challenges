#pragma once

#include "httplib.h"

void handle_root(const httplib::Request &req, httplib::Response &res);
void handle_health(const httplib::Request &req, httplib::Response &res);
void handle_summary(const httplib::Request &req, httplib::Response &res);
