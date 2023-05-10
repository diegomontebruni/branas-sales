package com.montebruni.sales.infra.repository.postgresql.adapter

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.domain.createOrder
import com.montebruni.sales.fixture.infra.repository.postgresql.createOrderItemPostgresqlModel
import com.montebruni.sales.fixture.infra.repository.postgresql.createOrderPostgresqlModel
import com.montebruni.sales.infra.repository.postgresql.model.OrderPostgresqlModel
import com.montebruni.sales.infra.repository.postgresql.port.ItemPostgresqlRepository
import com.montebruni.sales.infra.repository.postgresql.port.OrderPostgresqlRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*
import kotlin.IllegalArgumentException

class OrderPostgresqlAdapterTest(
    @MockK private val orderRepository: OrderPostgresqlRepository,
    @MockK private val orderItemRepository: ItemPostgresqlRepository
) : UnitTests() {

    @InjectMockKs
    private lateinit var orderAdapter: OrderPostgresqlAdapter

    @AfterEach
    fun tearDown() {
        confirmVerified(orderRepository, orderItemRepository)
    }

    @Test
    fun `should save an order and return nothing`() {
        val order = createOrder()
        val orderModelSlot = slot<OrderPostgresqlModel>()

        every { orderRepository.save(capture(orderModelSlot)) } returns createOrderPostgresqlModel()

        orderAdapter.save(order)

        assertEquals(order.id, orderModelSlot.captured.id)

        verify { orderRepository.save(orderModelSlot.captured) }
    }

    @Nested
    @DisplayName("Get last order number")
    inner class GetLastOrderNumberTestCases {

        @Test
        fun `should get last order number`() {
            val orderModel = createOrderPostgresqlModel()

            every { orderRepository.findTopByOrderByCreatedAtDesc() } returns orderModel

            val lastNumber = orderAdapter.getLastOrderNumber()

            assertEquals(orderModel.orderNumber, lastNumber)

            verify { orderRepository.findTopByOrderByCreatedAtDesc() }
        }

        @Test
        fun `should returns null when repository is empty and do not get the last order number`() {
            every { orderRepository.findTopByOrderByCreatedAtDesc() } returns null

            assertNull(orderAdapter.getLastOrderNumber())

            verify { orderRepository.findTopByOrderByCreatedAtDesc() }
        }
    }

    @Nested
    @DisplayName("find order by order number")
    inner class FindOrderByOrderNumberTestCases {

        @Test
        fun `should return order by order number`() {
            val orderModel = createOrderPostgresqlModel()
            val orderItems = listOf(createOrderItemPostgresqlModel())
            val orderIdSlot = slot<UUID>()

            every { orderRepository.findByOrderNumber(orderModel.orderNumber) } returns orderModel
            every { orderItemRepository.findByOrderId(capture(orderIdSlot)) } returns orderItems

            val order = orderAdapter.findByOrderNumber(orderNumber = orderModel.orderNumber)

            assertEquals(orderModel.id, order.id)
            assertEquals(orderModel.orderNumber, order.orderNumber.value)
            assertEquals(orderItems.size, order.items.size)
            assertEquals(orderItems.first().id, order.items.first().id)
            assertEquals(orderModel.id, orderIdSlot.captured)

            verify {
                orderRepository.findByOrderNumber(orderModel.orderNumber)
                orderItemRepository.findByOrderId(orderIdSlot.captured)
            }
        }

        @Test
        fun `should throw exception when try to get order by invalid order number`() {
            val orderModel = createOrderPostgresqlModel()

            every { orderRepository.findByOrderNumber(orderModel.orderNumber) } returns null

            assertThrows<IllegalArgumentException> { orderAdapter.findByOrderNumber(orderModel.orderNumber) }.run {
                assertEquals(this.message, "Order not found for order number ${orderModel.orderNumber}")
            }

            verify {
                orderRepository.findByOrderNumber(orderModel.orderNumber)
            }
        }

        @Test
        fun `should throw exception when try to get order by invalid order number and has not items`() {
            val orderModel = createOrderPostgresqlModel()
            val orderIdSlot = slot<UUID>()

            every { orderRepository.findByOrderNumber(orderModel.orderNumber) } returns orderModel
            every { orderItemRepository.findByOrderId(capture(orderIdSlot)) } returns null

            assertThrows<IllegalArgumentException> { orderAdapter.findByOrderNumber(orderModel.orderNumber) }.run {
                assertEquals(this.message, "Items not found for order id ${orderIdSlot.captured}")
            }

            assertEquals(orderModel.id, orderIdSlot.captured)

            verify {
                orderRepository.findByOrderNumber(orderModel.orderNumber)
                orderItemRepository.findByOrderId(orderIdSlot.captured)
            }
        }
    }

    @Nested
    @DisplayName("get orders")
    inner class GetOrdersTestCases {

        @Test
        fun `test getOrders with success`() {
            val orderModels = listOf(createOrderPostgresqlModel())
            val orderItems = listOf(createOrderItemPostgresqlModel())

            coEvery { orderRepository.findAll() } returns orderModels
            coEvery { orderItemRepository.findByOrderId(any()) } returns orderItems

            val orders = orderAdapter.getOrders()

            assertEquals(orderModels.size, orders.size)
            assertEquals(orderModels.first().id, orders.first().id)

            coVerify {
                orderRepository.findAll()
                orderItemRepository.findByOrderId(any())
            }
        }

        @Test
        fun `test getOrders with failure`() {
            val orderModels = listOf(createOrderPostgresqlModel())

            coEvery { orderRepository.findAll() } returns orderModels
            coEvery { orderItemRepository.findByOrderId(any()) } returns null

            assertThrows<IllegalArgumentException> { orderAdapter.getOrders() }.run {
                assertTrue(this.message!!.contains("Items not found for order id"))
            }

            coVerify {
                orderRepository.findAll()
                orderItemRepository.findByOrderId(any())
            }
        }
    }
}
