package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.createOrder
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createOrderMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.port.OrderMemoryRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OrderMemoryRepositoryAdapterTests(
    @MockK private val orderMemoryRepository: OrderMemoryRepository
) : UnitTests() {

    @InjectMockKs
    private lateinit var orderMemoryRepositoryAdapter : OrderMemoryRepositoryAdapter

    @Test
    fun `should save an order successfully`() {
        val order = createOrder()
        val orderModel = createOrderMemoryRepositoryModel()
        val saveSlot = slot<OrderMemoryRepositoryModel>()

        every { orderMemoryRepository.save(capture(saveSlot)) } returns orderModel

        val savedOrder = orderMemoryRepositoryAdapter.save(order)

        assertEquals(order.id, saveSlot.captured.id)
        assertEquals(order.id, savedOrder.id)
    }
}
