package com.montebruni.sales.domain.entity

import com.montebruni.sales.fixture.createOrderProduct
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OrderProductTests {

    @ParameterizedTest
    @ValueSource(ints = [-1, -100, -200])
    fun `should throw exception when quantity is invalid`(value: Int) {
        assertThrows<IllegalArgumentException> { createOrderProduct().copy(quantity = value) }
    }
}
