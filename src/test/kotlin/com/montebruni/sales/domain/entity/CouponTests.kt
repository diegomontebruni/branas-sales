package com.montebruni.sales.domain.entity

import com.montebruni.sales.domain.valueobjects.Amount
import com.montebruni.sales.fixture.domain.createCoupon
import com.montebruni.sales.fixture.domain.createExpiredCoupon
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
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
    @ValueSource(ints = [-1, 110, 120])
    fun `should throw exception when percentage is invalid`(value: Int) {
        assertThrows<IllegalArgumentException> { createCoupon().copy(percentage = value) }
    }

    @Test
    fun `should throw exception when has a expired coupon`() {
        assertFalse(createExpiredCoupon().isValid())
    }
}
