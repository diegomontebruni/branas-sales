package com.montebruni.sales.domain.entity

import com.montebruni.sales.fixture.createOrder
import com.montebruni.sales.fixture.createOrderWithCoupon
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OrderTests {

    private val order = createOrder()
    private val orderWithCoupon = createOrderWithCoupon()

    @Test
    fun `should calculate total amount successfully`() {
        val expectedTotalAmount = "3000.00"

        assertEquals(expectedTotalAmount, order.totalAmount.toString())
    }

    @Test
    fun `should calculate total amount when has a coupon`() {
        val expectedTotalAmount = "2700.00"

        assertEquals(expectedTotalAmount, orderWithCoupon.totalAmount.toString())
    }
}
