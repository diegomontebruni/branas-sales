package com.montebruni.sales.application.domain.entity

import com.montebruni.sales.fixture.domain.createOrderItem
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ItemTests {

    @ParameterizedTest
    @ValueSource(ints = [-1, -100, -200])
    fun `should throw exception when quantity is invalid`(value: Int) {
        assertThrows<IllegalArgumentException> { createOrderItem().copy(quantity = value) }
    }

    @Test
    fun `should calculate total amount`() {
        val expectedTotalAmount = "100.00"
        val orderItem = createOrderItem()

        val calculatedTotalAmount = orderItem.calculateTotalAmount()

        assertEquals(expectedTotalAmount, calculatedTotalAmount.toString())
    }
}
