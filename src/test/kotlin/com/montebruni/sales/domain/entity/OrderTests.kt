package com.montebruni.sales.domain.entity

import com.montebruni.sales.fixture.domain.createOrder
import com.montebruni.sales.fixture.domain.createOrderItem
import com.montebruni.sales.fixture.domain.createOrderWithCoupon
import com.montebruni.sales.fixture.domain.createProduct
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

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

    @Test
    fun `should throw exception when has a duplicated items`() {
        val duplicatedId = UUID.randomUUID()

        assertThrows<IllegalArgumentException> { order.copy(items = listOf(
            createOrderItem().copy(product = createProduct().copy(id = duplicatedId)),
            createOrderItem().copy(product = createProduct().copy(id = duplicatedId)),
            createOrderItem(),
            createOrderItem()
        )) }.let {
            assertEquals(it.message, "Has duplicated items on list")
        }
    }
}
