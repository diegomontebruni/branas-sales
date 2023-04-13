package com.montebruni.sales.infra.repositor.memoryrepository

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createOrderProductMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.impl.OrderProductMemoryRepositoryImpl
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.UUID

class OrderProductMemoryRepositoryTests : UnitTests() {

    @InjectMockKs
    private lateinit var orderProductMemoryRepositoryImpl: OrderProductMemoryRepositoryImpl

    @Test
    fun `should save an order product successfully`() {
        val orderProductModel = createOrderProductMemoryRepositoryModel()
        val savedOrderProduct = orderProductMemoryRepositoryImpl.save(orderProductModel)

        assertEquals(orderProductModel.id, savedOrderProduct.id)
        assertEquals(orderProductModel.orderId, savedOrderProduct.orderId)
        assertEquals(orderProductModel.description, savedOrderProduct.description)
        assertEquals(orderProductModel.price, savedOrderProduct.price)
        assertEquals(orderProductModel.quantity, savedOrderProduct.quantity)
    }

    @Test
    fun `should find an order product when has a valid id`() {
        val orderProductModel = createOrderProductMemoryRepositoryModel()
        orderProductMemoryRepositoryImpl.save(orderProductModel)

        val savedOrder = orderProductMemoryRepositoryImpl.findById(orderProductModel.id)

        assertNotNull(savedOrder)
        assertEquals(orderProductModel.id, savedOrder?.id)
    }

    @Test
    fun `should not find an order product when has an invalid id`() {
        val fakeId = UUID.randomUUID()
        val orderProductModel = createOrderProductMemoryRepositoryModel()
        orderProductMemoryRepositoryImpl.save(orderProductModel)

        val savedOrder = orderProductMemoryRepositoryImpl.findById(fakeId)

        assertNull(savedOrder)
    }

    @Test
    fun `should find all product orders when has a valid order Id`() {
        val orderId = UUID.randomUUID()
        listOf(
            createOrderProductMemoryRepositoryModel().copy(orderId = orderId),
            createOrderProductMemoryRepositoryModel().copy(orderId = orderId),
            createOrderProductMemoryRepositoryModel()
        ).forEach { orderProductMemoryRepositoryImpl.save(it)}

        val productOrders = orderProductMemoryRepositoryImpl.findByOrderId(orderId)

        assertTrue(productOrders.size == 2)
    }

    @Test
    fun `should find empty list when has an invalid order Id`() {
        val orderId = UUID.randomUUID()
        listOf(
            createOrderProductMemoryRepositoryModel(),
            createOrderProductMemoryRepositoryModel(),
            createOrderProductMemoryRepositoryModel()
        ).forEach { orderProductMemoryRepositoryImpl.save(it)}

        val productOrders = orderProductMemoryRepositoryImpl.findByOrderId(orderId)

        assertTrue(productOrders.isEmpty())
    }
}
