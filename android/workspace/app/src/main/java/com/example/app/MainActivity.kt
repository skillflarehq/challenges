package com.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app.ui.HomeScreen
import com.example.app.ui.StatusScreen
import com.example.app.ui.SummaryScreen

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme {
        AppTabs()
      }
    }
  }
}

@Composable
fun AppTabs() {
  val titles = listOf("Home", "Status", "Summary")
  var selected by remember { mutableIntStateOf(0) }
  Column(Modifier.fillMaxSize()) {
    TabRow(selectedTabIndex = selected) {
      titles.forEachIndexed { index, title ->
        Tab(
          selected = selected == index,
          onClick = { selected = index },
          text = { Text(title) },
        )
      }
    }
    val contentModifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
    when (selected) {
      0 -> HomeScreen(contentModifier)
      1 -> StatusScreen(contentModifier)
      2 -> SummaryScreen(contentModifier)
    }
  }
}
