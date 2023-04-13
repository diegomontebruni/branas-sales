package com.montebruni.sales.domain.entity

import com.montebruni.sales.fixture.createProduct
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ProductTests {

    @Test
    fun `should calculate total amount successfully`() {
        val product = createProduct()
        val expectedOutput = "30.00"

        assertEquals(expectedOutput, product.calculateTotalAmount().toString())
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -100, -200])
    fun `should throw exception when quantity is invalid`(value: Int) {
        assertThrows<IllegalArgumentException> { createProduct().copy(quantity = value) }
    }
}
