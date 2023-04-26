package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.application.domain.valueobjects.OrderNumber
import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.domain.createOrder
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createOrderMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.port.OrderMemoryRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
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

    @Nested
    @DisplayName("GetLastOrderNumber")
    inner class GetLastOrderNumberTestCases {

        @Test
        fun `should get the last order number successfully`() {
            val orderNumberOutput = OrderNumber()

            every { orderMemoryRepositoryAdapter.getLastOrderNumber() } returns orderNumberOutput.value

            val orderNumber = orderMemoryRepositoryAdapter.getLastOrderNumber()

            assertEquals(orderNumberOutput.value, orderNumber)

            verify { orderMemoryRepositoryAdapter.getLastOrderNumber() }
        }

        @Test
        fun `should return null when the last order number not exist`() {
            every { orderMemoryRepositoryAdapter.getLastOrderNumber() } returns null

            assertNull(orderMemoryRepositoryAdapter.getLastOrderNumber())

            verify { orderMemoryRepositoryAdapter.getLastOrderNumber() }
        }
    }
}
