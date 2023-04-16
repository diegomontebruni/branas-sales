package com.montebruni.sales.domain.entity

import com.montebruni.sales.domain.valueobjects.Amount
import com.montebruni.sales.fixture.createCoupon
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CouponTests {

    @Test
    fun `should calculate discount with valid percentage`() {
        val coupon = createCoupon()
        val totalAmount = Amount("100")
        val expectedOutput = Amount("90.0")

        assertEquals(expectedOutput.value, coupon.calculateDiscount(totalAmount).value)
    }

    @ParameterizedTest
    @ValueSource(longs = [-1L, 110L, 120L])
    fun `should throw exception when percentage is invalid`(value: Long) {
        assertThrows<IllegalArgumentException> { createCoupon().copy(percentage = value) }
    }
}
