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
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GetAllOrdersTest(
    @MockK private val orderRepository: OrderRepository
) : UnitTests() {

    @InjectMockKs
    private lateinit var getAllOrders: GetAllOrders

    @AfterEach
    internal fun tearDown() {
        confirmVerified(orderRepository)
    }

    @Test
    fun `should get all orders`() {
        val ordersOutput = listOf(
            createOrder(),
            createOrder(),
            createOrder()
        )

        every { orderRepository.getOrders() } returns ordersOutput

        val response = getAllOrders.execute()

        assertEquals(ordersOutput.size, response.size)
        assertEquals(ordersOutput.first().id, response.first().id)

        verify { orderRepository.getOrders() }
    }

    @Test
    fun `should return empty list when has no orders to retrieve`() {
        every { orderRepository.getOrders() } returns emptyList()

        val response = getAllOrders.execute()

        assertTrue(response.isEmpty())

        verify { orderRepository.getOrders() }
    }
}
