package com.montebruni.sales.application.usecase

import com.montebruni.sales.application.domain.port.OrderRepository
import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.domain.createOrder
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Year

class FindOrderByOrderNumberTest(
    @MockK private val orderRepository: OrderRepository,
) : UnitTests() {

    @InjectMockKs
    private lateinit var findOrderByOrderNumber: FindOrderByOrderNumber

    @AfterEach
    internal fun tearDown() {
        confirmVerified(orderRepository)
    }

    private val orderNumber = "${Year.now().value}00000000"

    @Test
    fun `should returns order when has a valid order number`() {
        val order = createOrder()

        every { orderRepository.findByOrderNumber(orderNumber) } returns order

        val result = findOrderByOrderNumber.execute(orderNumber)

        assertEquals(orderNumber, result.orderNumber)
        assertEquals(order.id, result.id)
        assertEquals(order.orderNumber.value, result.orderNumber)
        assertEquals(order.document.value, result.document)
        assertEquals(order.totalAmount.value.toDouble(), result.totalAmount)
        assertEquals(order.items.size, result.items.size)
        assertEquals(order.items.first().id, result.items.first().id)
        assertNull(result.coupon)

        verify { orderRepository.findByOrderNumber(orderNumber) }
    }

    @Test
    fun `should throw exception when has an invalid order number`() {
        every { orderRepository.findByOrderNumber(orderNumber) } throws IllegalArgumentException()

        assertThrows<IllegalArgumentException> { findOrderByOrderNumber.execute(orderNumber) }

        verify { orderRepository.findByOrderNumber(orderNumber) }
    }
}
