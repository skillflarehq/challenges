package com.example.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// Implement these screens. Do not change the public destinations.
//
// Home    -> show R.string.greeting ("Hello from Android")
// Status  -> show R.string.status_text ("ok")
// Summary -> read asset "items.csv" (app/src/main/assets/items.csv).
//            Display group, total, and skipped from Summary.fromCsv.
//
// For /summary, skip the header row. Group by `category` and sum
// numeric `amount`. Skip rows with an empty category or a non-numeric
// amount. The winner is the category with the highest total; ties go to
// the lexicographically smallest category name.

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
  Box(modifier) {
    Text("not implemented")
  }
}

@Composable
fun StatusScreen(modifier: Modifier = Modifier) {
  Box(modifier) {
    Text("not implemented")
  }
}

@Composable
fun SummaryScreen(modifier: Modifier = Modifier) {
  Box(modifier) {
    Text("not implemented")
  }
}
