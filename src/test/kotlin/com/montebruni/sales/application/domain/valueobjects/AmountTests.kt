package com.montebruni.sales.application.domain.valueobjects

import com.montebruni.sales.application.domain.valueobjects.Amount
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class AmountTests {

    @Test
    fun `should create amount successfully`() {
        Amount("10")
        Amount(20.0)

        assertEquals(BigDecimal.ZERO, Amount().value)
    }

    @Test
    fun `should rounding values with 2 decimal places`() {
        assertEquals("20.34", Amount(20.345).toString())
    }

    @Test
    fun `should multiplied amount from an int`() {
        val expectedOutput = Amount("10")
        val amount = Amount("5")
        val multiplier = 2

        assertEquals(expectedOutput, amount.multiply(multiplier))
    }

    @Test
    fun `should get percentage value from a long`() {
        val expectedOutput = Amount(90.0)
        val percent = 10
        val totalAmount = Amount("100")

        assertEquals(expectedOutput.value, totalAmount.percentage(percent).value)
    }

    @Test
    fun `should throw exception when value is lower than zero`() {
        assertThrows<IllegalArgumentException> { Amount("-1") }
    }
}
