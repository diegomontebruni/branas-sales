package com.montebruni.sales.infra.repositor.memoryrepository

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.extensions.domain.entity.toOrderItemMemoryRepositoryModel
import com.montebruni.sales.fixture.createOrderItem
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createOrderItemMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.OrderItemMemoryRepositoryAdapter
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderItemMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.port.OrderItemMemoryRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class OrderItemMemoryRepositoryAdapterTests(
    @MockK private val orderItemMemoryRepository: OrderItemMemoryRepository
) : UnitTests() {

    @InjectMockKs
    private lateinit var orderItemMemoryRepositoryAdapter: OrderItemMemoryRepositoryAdapter

    @Test
    fun `should save an order item successfully when has a valid order item`() {
        val orderItem = createOrderItem()
        val expectedOutput = orderItem.toOrderItemMemoryRepositoryModel()
        val saveSlot = slot<OrderItemMemoryRepositoryModel>()

        every { orderItemMemoryRepository.save(capture(saveSlot)) } returns expectedOutput

        val savedOrderItem = orderItemMemoryRepositoryAdapter.save(orderItem)

        assertEquals(orderItem.id, saveSlot.captured.id)
        assertEquals(orderItem.id, savedOrderItem.id)
    }

    @Test
    fun `should find order item when has a valid id`() {
        val orderItemId = UUID.randomUUID()
        val expectedOutput = createOrderItemMemoryRepositoryModel().copy(id = orderItemId)

        every { orderItemMemoryRepository.findById(orderItemId) } returns expectedOutput

        val orderItem = orderItemMemoryRepositoryAdapter.findById(orderItemId)

        assertEquals(orderItemId, orderItem.id)
    }

    @Test
    fun `should throw exception when has an invalid id`() {
        val orderItemId = UUID.randomUUID()

        every { orderItemMemoryRepository.findById(orderItemId) } returns null

        assertThrows<IllegalArgumentException> { orderItemMemoryRepositoryAdapter.findById(orderItemId) }
    }

    @Test
    fun `should find all order items when has a valid order id`() {
        val orderId = UUID.randomUUID()
        val orderItemList = mutableListOf(
            createOrderItemMemoryRepositoryModel().copy(orderId = orderId),
            createOrderItemMemoryRepositoryModel().copy(orderId = orderId)
        )

        every { orderItemMemoryRepository.findByOrderId(orderId) } returns orderItemList

        val orderItems = orderItemMemoryRepositoryAdapter.findByOrderId(orderId)

        assertTrue(orderItems.isNotEmpty())
    }

    @Test
    fun `should find empty array when has an invalid order id`() {
        val orderId = UUID.randomUUID()

        every { orderItemMemoryRepository.findByOrderId(orderId) } returns emptyList()

        val orderItems = orderItemMemoryRepositoryAdapter.findByOrderId(orderId)

        assertTrue(orderItems.isEmpty())
    }
}
