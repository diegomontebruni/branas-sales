package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.application.domain.valueobjects.OrderNumber
import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.domain.createOrder
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createCouponMemoryRepositoryModel
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createOrderItemMemoryRepositoryModel
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createOrderMemoryRepositoryModel
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createOrderWithCouponMemoryRepositoryModel
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createProductMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderItemMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.port.CouponMemoryRepository
import com.montebruni.sales.infra.repository.memoryrepository.port.OrderItemMemoryRepository
import com.montebruni.sales.infra.repository.memoryrepository.port.OrderMemoryRepository
import com.montebruni.sales.infra.repository.memoryrepository.port.ProductMemoryRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class OrderMemoryRepositoryAdapterTests(
    @MockK private val orderRepository: OrderMemoryRepository,
    @MockK private val orderItemRepository: OrderItemMemoryRepository,
    @MockK private val productRepository: ProductMemoryRepository,
    @MockK private val couponRepository: CouponMemoryRepository
) : UnitTests() {

    @InjectMockKs
    private lateinit var orderMemoryRepositoryAdapter : OrderMemoryRepositoryAdapter

    @AfterEach
    internal fun tearDown() {
        confirmVerified(orderRepository, orderItemRepository, productRepository, couponRepository)
    }

    @Test
    fun `should save an order successfully`() {
        val order = createOrder()
        val saveSlot = slot<OrderMemoryRepositoryModel>()
        val itemsSlot = mutableListOf<OrderItemMemoryRepositoryModel>()

        every { orderRepository.save(capture(saveSlot)) } answers { saveSlot.captured }
        every { orderItemRepository.save(capture(itemsSlot)) } answers { itemsSlot.captured() }

        val savedOrder = orderMemoryRepositoryAdapter.save(order)

        assertEquals(order.id, saveSlot.captured.id)
        assertEquals(order.id, savedOrder.id)
        assertEquals(3, itemsSlot.size)

        verify { orderRepository.save(saveSlot.captured) }
        itemsSlot.forEach { verify { orderItemRepository.save(it) } }
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

    @Nested
    @DisplayName("FindByOrderNumber")
    inner class FindByOrderNumberTestCases {

        @Test
        fun `should return an order when has a valid order number`() {
            val orderNumber = "202300000000"

            val orderItemSlot = mutableListOf<UUID>()
            val productSlot = mutableListOf<UUID>()
            val couponSlot = slot<String>()

            val orderOutput = createOrderMemoryRepositoryModel()
            val productOutput = listOf(
                createProductMemoryRepositoryModel(),
                createProductMemoryRepositoryModel().copy(id = UUID.randomUUID())
            )
            val coupon = createCouponMemoryRepositoryModel()
            val orderItemOutput = listOf(
                createOrderItemMemoryRepositoryModel().copy(
                    orderId = orderOutput.id, productId = productOutput.first().id),
                createOrderItemMemoryRepositoryModel().copy(
                    orderId = orderOutput.id,  productId = productOutput.last().id)
            )

            every { orderRepository.findByOrderNumber(orderNumber) } returns orderOutput
            every { orderItemRepository.findByOrderId(capture(orderItemSlot)) }.returnsMany(orderItemOutput)
            every { productRepository.findById(capture(productSlot)) } returns
                productOutput.first() andThen productOutput.last()
            every { couponRepository.findByCode(capture(couponSlot)) } returns coupon

            val result = orderMemoryRepositoryAdapter.findByOrderNumber(orderNumber)

            assertEquals(orderNumber, result.orderNumber.value)
            assertEquals(2, result.items.size)
            assertEquals(productOutput.first().id, result.items.first().product.id)
            assertNull(result.coupon)

            orderItemSlot.forEach { verify { orderItemRepository.findByOrderId(it) } }
            productSlot.forEach { verify { productRepository.findById(it) } }

            verify {
                orderRepository.findByOrderNumber(orderNumber)
            }
        }

        @Test
        fun `should return an order with coupon when has a valid order number`() {
            val orderNumber = "202300000000"

            val orderItemSlot = mutableListOf<UUID>()
            val productSlot = mutableListOf<UUID>()
            val couponSlot = slot<String>()

            val orderOutput = createOrderWithCouponMemoryRepositoryModel()
            val productOutput = listOf(
                createProductMemoryRepositoryModel(),
                createProductMemoryRepositoryModel().copy(id = UUID.randomUUID())
            )
            val coupon = createCouponMemoryRepositoryModel().copy(code = "DESC10")
            val orderItemOutput = listOf(
                createOrderItemMemoryRepositoryModel().copy(
                    orderId = orderOutput.id, productId = productOutput.first().id),
                createOrderItemMemoryRepositoryModel().copy(
                    orderId = orderOutput.id,  productId = productOutput.last().id)
            )

            every { orderRepository.findByOrderNumber(orderNumber) } returns orderOutput
            every { orderItemRepository.findByOrderId(capture(orderItemSlot)) }.returnsMany(orderItemOutput)
            every { productRepository.findById(capture(productSlot)) } returns
                productOutput.first() andThen productOutput.last()
            every { couponRepository.findByCode(capture(couponSlot)) } returns coupon

            val result = orderMemoryRepositoryAdapter.findByOrderNumber(orderNumber)

            assertEquals(orderNumber, result.orderNumber.value)
            assertEquals(2, result.items.size)
            assertEquals(productOutput.first().id, result.items.first().product.id)
            assertEquals(couponSlot.captured, result.coupon!!.code)

            orderItemSlot.forEach { verify { orderItemRepository.findByOrderId(it) } }
            productSlot.forEach { verify { productRepository.findById(it) } }

            verify {
                orderRepository.findByOrderNumber(orderNumber)
                couponRepository.findByCode(couponSlot.captured)
            }
        }

        @Test
        fun `should throw error when has an invalid order number`() {
            val orderNumber = "123"
            every { orderRepository.findByOrderNumber(orderNumber) } returns null

            assertThrows<IllegalArgumentException> { orderMemoryRepositoryAdapter.findByOrderNumber(orderNumber) }.run {
                assertEquals("Order not found for order number", this.message)
            }

            verify { orderRepository.findByOrderNumber(orderNumber) }
        }
    }
}
