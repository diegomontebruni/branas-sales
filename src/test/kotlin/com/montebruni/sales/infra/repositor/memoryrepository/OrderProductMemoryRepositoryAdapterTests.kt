package com.montebruni.sales.infra.repositor.memoryrepository

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.extensions.domain.entity.toOrderProductMemoryRepositoryModel
import com.montebruni.sales.fixture.createOrderProduct
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createOrderProductMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.OrderProductMemoryRepositoryAdapter
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderProductMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.port.OrderProductMemoryRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class OrderProductMemoryRepositoryAdapterTests(
    @MockK private val orderProductMemoryRepository: OrderProductMemoryRepository
) : UnitTests() {

    @InjectMockKs
    private lateinit var orderProductMemoryRepositoryAdapter: OrderProductMemoryRepositoryAdapter

    @Test
    fun `should save an order product successfully when has a valid order product`() {
        val orderProduct = createOrderProduct()
        val expectedOutput = orderProduct.toOrderProductMemoryRepositoryModel()
        val saveSlot = slot<OrderProductMemoryRepositoryModel>()

        every { orderProductMemoryRepository.save(capture(saveSlot)) } returns expectedOutput

        val savedOrderProduct = orderProductMemoryRepositoryAdapter.save(orderProduct)

        assertEquals(orderProduct.id, saveSlot.captured.id)
        assertEquals(orderProduct.id, savedOrderProduct.id)
    }

    @Test
    fun `should find order product when has a valid id`() {
        val orderProductId = UUID.randomUUID()
        val expectedOutput = createOrderProductMemoryRepositoryModel().copy(id = orderProductId)

        every { orderProductMemoryRepository.findById(orderProductId) } returns expectedOutput

        val orderProduct = orderProductMemoryRepositoryAdapter.findById(orderProductId)

        assertEquals(orderProductId, orderProduct.id)
    }

    @Test
    fun `should throw exception when has an invalid id`() {
        val orderProductId = UUID.randomUUID()

        every { orderProductMemoryRepository.findById(orderProductId) } returns null

        assertThrows<IllegalArgumentException> { orderProductMemoryRepositoryAdapter.findById(orderProductId) }
    }

    @Test
    fun `should find all order products when has a valid order id`() {
        val orderId = UUID.randomUUID()
        val orderProductList = mutableListOf(
            createOrderProductMemoryRepositoryModel().copy(orderId = orderId),
            createOrderProductMemoryRepositoryModel().copy(orderId = orderId)
        )

        every { orderProductMemoryRepository.findByOrderId(orderId) } returns orderProductList

        val orderProducts = orderProductMemoryRepositoryAdapter.findByOrderId(orderId)

        assertTrue(orderProducts.isNotEmpty())
    }

    @Test
    fun `should find empty array when has an invalid order id`() {
        val orderId = UUID.randomUUID()

        every { orderProductMemoryRepository.findByOrderId(orderId) } returns emptyList()

        val orderProducts = orderProductMemoryRepositoryAdapter.findByOrderId(orderId)

        assertTrue(orderProducts.isEmpty())
    }
}
