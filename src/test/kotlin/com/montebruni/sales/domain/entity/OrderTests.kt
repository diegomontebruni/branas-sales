package com.montebruni.sales.domain.entity

import com.montebruni.sales.fixture.createOrder
import com.montebruni.sales.fixture.createOrderWithCoupon
import com.montebruni.sales.fixture.createProduct
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OrderTests {

    private val order = createOrder()
    private val orderWithCoupon = createOrderWithCoupon()

    @Test
    fun `should calculate total amount successfully`() {
        val expectedTotalAmount = "90.00"

        assertEquals(expectedTotalAmount, order.totalAmount.toString())
    }

    @Test
    fun `should calculate total amount when add a new product`() {
        val expectedTotalAmount = "120.00"
        order.addProduct(createProduct())

        assertEquals(expectedTotalAmount, order.totalAmount.toString())
    }

    @Test
    fun `should calculate total amount when has a coupon`() {
        val expectedTotalAmount = "81.00"

        assertEquals(expectedTotalAmount, orderWithCoupon.totalAmount.toString())
    }

    @Test
    fun `should calculate total amount when add a new product and has a coupon`() {
        val expectedTotalAmount = "108.00"
        orderWithCoupon.addProduct(createProduct())

        assertEquals(expectedTotalAmount, orderWithCoupon.totalAmount.toString())
    }
}
