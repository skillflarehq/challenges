package com.example.app

data class SummaryResult(
  val group: String,
  val total: Double,
  val skipped: Int,
)

object Summary {
  // Implement fromCsv. Do not change the return type.
  //
  // Skip the header row. Group by `category` and sum numeric `amount`.
  // Skip rows with an empty category or a non-numeric amount. The winner
  // is the category with the highest total; ties go to the
  // lexicographically smallest category name.
  //
  // For starter assets/items.csv the expected result is
  // group=electronics, total=65.5, skipped=2.

  fun fromCsv(text: String): SummaryResult {
    throw NotImplementedError("not implemented")
  }
}
