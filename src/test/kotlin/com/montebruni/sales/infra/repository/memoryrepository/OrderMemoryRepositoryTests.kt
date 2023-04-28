package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.application.domain.valueobjects.OrderNumber
import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createOrderMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.impl.OrderMemoryRepositoryImpl
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class OrderMemoryRepositoryTests : UnitTests() {

    @InjectMockKs
    private lateinit var orderMemoryRepositoryImpl: OrderMemoryRepositoryImpl

    @Test
    fun `should save order successfully`() {
        val orderModel = createOrderMemoryRepositoryModel()

        val savedOrder = orderMemoryRepositoryImpl.save(orderModel)

        assertEquals(orderModel.id, savedOrder.id)
    }

    @Nested
    @DisplayName("GetLastOrderNumber")
    inner class GetLastOrderNumberTestCases {

        @Test
        fun `should get the last order number when has a not empty list`() {
            val ordersModel = listOf(
                createOrderMemoryRepositoryModel(),
                createOrderMemoryRepositoryModel().copy(orderNumber = OrderNumber().increment().value)
            ).onEach { orderMemoryRepositoryImpl.save(it) }

            val lastOrderNumber = orderMemoryRepositoryImpl.getLastOrderNumber()

            assertEquals(ordersModel.last().orderNumber, lastOrderNumber)
        }

        @Test
        fun `should return null when has a empty list`() {
            assertNull(orderMemoryRepositoryImpl.getLastOrderNumber())
        }
    }

    @Nested
    @DisplayName("FindByOrderNumber")
    inner class FindByOrderNumberTestCases {

        @Test
        fun `should get order model when has a valid order number`() {
            val ordersModel = listOf(
                createOrderMemoryRepositoryModel(),
                createOrderMemoryRepositoryModel().copy(orderNumber = OrderNumber().increment().value)
            ).onEach { orderMemoryRepositoryImpl.save(it) }

            val order = orderMemoryRepositoryImpl.findByOrderNumber(ordersModel.last().orderNumber)

            assertNotNull(order)
            assertEquals(ordersModel.last().id, order!!.id)
        }

        @Test
        fun `should return null when has an invalid order number`() {
            val orderNumber = "123"

            listOf(
                createOrderMemoryRepositoryModel(),
                createOrderMemoryRepositoryModel().copy(orderNumber = OrderNumber().increment().value)
            ).onEach { orderMemoryRepositoryImpl.save(it) }

            val order = orderMemoryRepositoryImpl.findByOrderNumber(orderNumber)

            assertNull(order)
        }
    }
}
