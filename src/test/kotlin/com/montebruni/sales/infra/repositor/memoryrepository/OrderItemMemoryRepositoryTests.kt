package com.montebruni.sales.infra.repositor.memoryrepository

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createOrderItemMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.impl.OrderItemMemoryRepositoryImpl
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.UUID

class OrderItemMemoryRepositoryTests : UnitTests() {

    @InjectMockKs
    private lateinit var orderItemMemoryRepositoryImpl: OrderItemMemoryRepositoryImpl

    @Test
    fun `should save an order item successfully`() {
        val orderItemModel = createOrderItemMemoryRepositoryModel()
        val savedOrderItem = orderItemMemoryRepositoryImpl.save(orderItemModel)

        assertEquals(orderItemModel.id, savedOrderItem.id)
        assertEquals(orderItemModel.orderId, savedOrderItem.orderId)
        assertEquals(orderItemModel.description, savedOrderItem.description)
        assertEquals(orderItemModel.price, savedOrderItem.price)
        assertEquals(orderItemModel.quantity, savedOrderItem.quantity)
    }

    @Test
    fun `should find an order item when has a valid id`() {
        val orderItemModel = createOrderItemMemoryRepositoryModel()
        orderItemMemoryRepositoryImpl.save(orderItemModel)

        val savedOrder = orderItemMemoryRepositoryImpl.findById(orderItemModel.id)

        assertNotNull(savedOrder)
        assertEquals(orderItemModel.id, savedOrder?.id)
    }

    @Test
    fun `should not find an order item when has an invalid id`() {
        val fakeId = UUID.randomUUID()
        val orderItemModel = createOrderItemMemoryRepositoryModel()
        orderItemMemoryRepositoryImpl.save(orderItemModel)

        val savedOrder = orderItemMemoryRepositoryImpl.findById(fakeId)

        assertNull(savedOrder)
    }

    @Test
    fun `should find all item orders when has a valid order Id`() {
        val orderId = UUID.randomUUID()
        listOf(
            createOrderItemMemoryRepositoryModel().copy(orderId = orderId),
            createOrderItemMemoryRepositoryModel().copy(orderId = orderId),
            createOrderItemMemoryRepositoryModel()
        ).forEach { orderItemMemoryRepositoryImpl.save(it)}

        val itemOrders = orderItemMemoryRepositoryImpl.findByOrderId(orderId)

        assertTrue(itemOrders.size == 2)
    }

    @Test
    fun `should find empty list when has an invalid order Id`() {
        val orderId = UUID.randomUUID()
        listOf(
            createOrderItemMemoryRepositoryModel(),
            createOrderItemMemoryRepositoryModel(),
            createOrderItemMemoryRepositoryModel()
        ).forEach { orderItemMemoryRepositoryImpl.save(it)}

        val itemOrders = orderItemMemoryRepositoryImpl.findByOrderId(orderId)

        assertTrue(itemOrders.isEmpty())
    }
}
