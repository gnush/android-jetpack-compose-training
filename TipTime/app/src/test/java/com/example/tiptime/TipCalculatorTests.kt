package com.example.tiptime

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {
    @Test
    fun calculateTip_20PercentNoRoundup() {
        val amount = 10.0
        val tipPercentMultiplier = 0.2

        val expected = NumberFormat.getCurrencyInstance().format(2)

        assertEquals(expected, calculateTip(amount, tipPercentMultiplier, false))
    }

    @Test
    fun calculateTip_20PercentRoundup() {
        val amount = 10.0
        val tipPercentMultiplier = 0.19

        val expected = NumberFormat.getCurrencyInstance().format(2)

        assertEquals(expected, calculateTip(amount, tipPercentMultiplier, true))
    }
}