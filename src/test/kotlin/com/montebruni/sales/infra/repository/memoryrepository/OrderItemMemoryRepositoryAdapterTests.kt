package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.createOrderItem
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createOrderItemMemoryRepositoryModel
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createProductMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderItemMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.port.OrderItemMemoryRepository
import com.montebruni.sales.infra.repository.memoryrepository.port.ProductMemoryRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class OrderItemMemoryRepositoryAdapterTests(
    @MockK private val orderItemMemoryRepository: OrderItemMemoryRepository,
    @MockK private val productMemoryRepository: ProductMemoryRepository
) : UnitTests() {

    @InjectMockKs
    private lateinit var orderItemMemoryRepositoryAdapter: OrderItemMemoryRepositoryAdapter

    @AfterEach
    internal fun tearDown() {
        confirmVerified(orderItemMemoryRepository, productMemoryRepository)
    }

    @Test
    fun `should save an order item successfully when has a valid order item`() {
        val orderItem = createOrderItem()
        val saveSlot = slot<OrderItemMemoryRepositoryModel>()

        every { orderItemMemoryRepository.save(capture(saveSlot)) } answers { saveSlot.captured }

        val savedOrderItem = orderItemMemoryRepositoryAdapter.save(orderItem)

        assertEquals(orderItem.id, saveSlot.captured.id)
        assertEquals(orderItem.id, savedOrderItem.id)

        verify {
            orderItemMemoryRepository.save(saveSlot.captured)
        }
    }

    @Test
    fun `should find order item when has a valid id`() {
        val orderItemId = UUID.randomUUID()
        val expectedOutput = createOrderItemMemoryRepositoryModel().copy(id = orderItemId)
        val expectedProductOutput = createProductMemoryRepositoryModel()
        val productSlot = slot<UUID>()

        every { orderItemMemoryRepository.findById(orderItemId) } returns expectedOutput
        every { productMemoryRepository.findById(capture(productSlot)) } returns expectedProductOutput

        val orderItem = orderItemMemoryRepositoryAdapter.findById(orderItemId)

        assertEquals(orderItemId, orderItem.id)
        assertEquals(expectedProductOutput.id, orderItem.product.id)

        verify {
            orderItemMemoryRepository.findById(orderItemId)
            productMemoryRepository.findById(productSlot.captured)
        }
    }

    @Test
    fun `should throw exception when has an invalid id`() {
        val orderItemId = UUID.randomUUID()

        every { orderItemMemoryRepository.findById(orderItemId) } returns null

        assertThrows<IllegalArgumentException> { orderItemMemoryRepositoryAdapter.findById(orderItemId) }

        verify {
            orderItemMemoryRepository.findById(orderItemId)
        }
    }

    @Test
    fun `should find all order items when has a valid order id`() {
        val orderId = UUID.randomUUID()
        val orderItemList = mutableListOf(
            createOrderItemMemoryRepositoryModel().copy(orderId = orderId),
            createOrderItemMemoryRepositoryModel().copy(orderId = orderId)
        )
        val expectedProductOutput = createProductMemoryRepositoryModel()

        val productSlot = mutableListOf<UUID>()

        every { orderItemMemoryRepository.findByOrderId(orderId) } returns orderItemList
        every { productMemoryRepository.findById(capture(productSlot)) } returns expectedProductOutput

        val orderItems = orderItemMemoryRepositoryAdapter.findByOrderId(orderId)

        assertTrue(orderItems.isNotEmpty())
        assertEquals(expectedProductOutput.id, orderItems.first().product.id)

        verify {
            orderItemMemoryRepository.findByOrderId(orderId)
        }

        productSlot.forEach {
            verify { productMemoryRepository.findById(it) }
        }
    }

    @Test
    fun `should find empty array when has an invalid order id`() {
        val orderId = UUID.randomUUID()

        every { orderItemMemoryRepository.findByOrderId(orderId) } returns emptyList()

        val orderItems = orderItemMemoryRepositoryAdapter.findByOrderId(orderId)

        assertTrue(orderItems.isEmpty())

        verify { orderItemMemoryRepository.findByOrderId(orderId) }
    }
}
