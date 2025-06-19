package com.example.testeableapp

import com.example.testeableapp.ui.Screens.calculateTip
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for the calculateTip() function and total per person logic.
 * This class verifies expected behaviors with various input scenarios.
 */
class CalculateTipTest {

    /**
     * Tests tip calculation with 37% and rounding enabled.
     * Expected result: 37.0 (already a whole number, no change after ceil).
     */
    @Test
    fun calculateTip_with37PercentAndRoundUp_returnsCorrectValue() {
        // Arrange
        val amount = 100.0
        val tipPercent = 37
        val roundUp = true

        // Act
        val result = calculateTip(amount, tipPercent, roundUp)

        // Assert
        assertEquals(37.0, result, 0.01)
    }

    /**
     * Tests that negative amounts return 0 as tip.
     */
    @Test
    fun calculateTip_withNegativeAmount_returnsZero() {
        val result = calculateTip(-50.0, 20, false)
        assertEquals(0.0, result, 0.01)
    }

    /**
     * Tests correct division of total amount among multiple people.
     */
    @Test
    fun totalPerPerson_dividesCorrectly() {
        val amount = 100.0
        val tip = calculateTip(amount, 20, false)
        val people = 4
        val totalPerPerson = (amount + tip) / people
        assertEquals(30.0, totalPerPerson, 0.01)
    }

    /**
     * Tests that when number of people is 0, total per person returns 0 to avoid division by zero.
     */
    @Test
    fun totalPerson_withZeroPeople_returnsZero() {
        val amount = 100.0
        val tip = calculateTip(amount, 20, false)
        val people = 0
        val totalPerPerson = if (people > 0) (amount + tip) / people else 0.0
        assertEquals(0.0, totalPerPerson, 0.01)
    }

    /**
     * Tests that when tip percentage is 0, the result is 0.
     */
    @Test
    fun tipCalculator_withZeroPercent_returnsZero() {
        val amount = 100.0
        val tipPercent = 0
        val roundUp = false
        val result = calculateTip(amount, tipPercent, roundUp)
        assertEquals(0.0, result, 0.01)
    }

    /**
     * Tests that tip calculation without rounding preserves decimal values correctly.
     */
    @Test
    fun tipCalculator_withoutRounding_keepsDecimals() {
        val amount = 85.0  // 15% = 12.75
        val tipPercent = 15
        val roundUp = false

        val result = calculateTip(amount, tipPercent, roundUp)

        assertEquals(12.75, result, 0.01)
    }

}
