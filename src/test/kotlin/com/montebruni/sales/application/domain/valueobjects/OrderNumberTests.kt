package com.montebruni.sales.application.domain.valueobjects

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.Year

class OrderNumberTests {

    private val year = Year.now().value

    @Test
    fun `should create an order number with empty input value`() {
        assertEquals("${year}00000000", OrderNumber().value)
    }

    @ParameterizedTest
    @ValueSource(strings = ["202300000001", "202300000002", "202311111111"])
    fun `should increment an order number when has a last code`(lastCode: String) {
        assertEquals(lastCode.toLong().inc().toString(), OrderNumber(lastCode).increment().value)
    }

    @Test
    fun `should throw exception when has a invalid last order number`() {
        val lastCode = "2023000000A0"
        assertThrows<IllegalArgumentException> { OrderNumber(lastCode).increment() }
    }
}
