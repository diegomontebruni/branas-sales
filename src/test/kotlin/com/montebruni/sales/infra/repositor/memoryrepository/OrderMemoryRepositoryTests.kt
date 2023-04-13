package com.montebruni.sales.infra.repositor.memoryrepository

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createOrderMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.impl.OrderMemoryRepositoryImpl
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.Assertions.assertEquals
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
}
