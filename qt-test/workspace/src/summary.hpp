#pragma once

#include <string>

struct SummaryResult {
  std::string group;
  double total = 0;
  int skipped = 0;
};

// Implement fromCsv. Do not change the return type.
//
// Skip the header row. Group by `category` and sum numeric `amount`.
// Skip rows with an empty category or a non-numeric amount. The winner
// is the category with the highest total; ties go to the
// lexicographically smallest category name.
//
// For starter data/items.csv the expected result is
// group=electronics, total=65.5, skipped=2.
SummaryResult fromCsv(const std::string &text);
