package com.example.app

import org.junit.Assert.assertEquals
import org.junit.Test

class SummaryTest {
  private val csv =
      """
      category,sku,amount
      electronics,kbd-1,40.00
      electronics,mouse-2,25.50
      grocery,milk-9,4.25
      grocery,bread-3,3.75
      hardware,hammer-1,18.00
      hardware,nails-4,6.50
      office,pen-7,2.00
      electronics,bad-row,not-a-number
      ,orphan,10.00
      grocery,eggs-2,5.00
      """.trimIndent()

  @Test
  fun fromCsv_matchesStarterContract() {
    val result = Summary.fromCsv(csv)
    assertEquals("electronics", result.group)
    assertEquals(65.5, result.total, 0.001)
    assertEquals(2, result.skipped)
  }
}
