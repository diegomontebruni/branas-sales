package com.montebruni.sales.application.usecase

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.application.domain.entity.Order
import com.montebruni.sales.application.domain.port.CouponRepository
import com.montebruni.sales.application.domain.port.OrderRepository
import com.montebruni.sales.application.domain.port.ProductRepository
import com.montebruni.sales.application.domain.valueobjects.OrderNumber
import com.montebruni.sales.fixture.domain.createCoupon
import com.montebruni.sales.fixture.domain.createExpiredCoupon
import com.montebruni.sales.fixture.domain.createProduct
import com.montebruni.sales.fixture.usecase.createCreateOrderInput
import com.montebruni.sales.fixture.usecase.createCreateOrderWithCouponInput
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class CreateOrderTest(
    @MockK private val orderRepository: OrderRepository,
    @MockK private val couponRepository: CouponRepository,
    @MockK private val productRepository: ProductRepository,
) : UnitTests() {

    @InjectMockKs
    private lateinit var useCase: CreateOrder

    @AfterEach
    internal fun tearDown() {
        confirmVerified(orderRepository, couponRepository, productRepository)
    }

    @Test
    fun `should returns correct order number when create a first order`() {
        val input = createCreateOrderInput()
        val expectedTotalAmount = "6.55"
        val orderNumberOutput = OrderNumber()

        val orderRepositorySlot = slot<Order>()
        val productIdSlot = mutableListOf<UUID>()

        every { orderRepository.save(capture(orderRepositorySlot)) } answers { orderRepositorySlot.captured }
        every { orderRepository.getLastOrderNumber() } returns null
        every {
            productRepository.findById(capture(productIdSlot))
        } returns
            createProduct().copy(id = UUID.randomUUID()) andThen
            createProduct().copy(id = UUID.randomUUID()) andThen
            createProduct().copy(id = UUID.randomUUID())

        val output = useCase.execute(input)

        assertEquals(orderNumberOutput.value, output.orderNumber)
        assertEquals(input.document, orderRepositorySlot.captured.document.value)
        assertEquals(input.items.size, orderRepositorySlot.captured.items.size)
        assertNull(orderRepositorySlot.captured.coupon)
        assertEquals(expectedTotalAmount, output.totalAmount.toString())
        assertEquals(expectedTotalAmount, orderRepositorySlot.captured.totalAmount.toString())

        verify {
            orderRepository.save(orderRepositorySlot.captured)
            productRepository.findById(capture(productIdSlot))
            orderRepository.getLastOrderNumber()
        }
    }

    @Test
    fun `should create an order when has a valid input without coupon`() {
        val input = createCreateOrderInput()
        val expectedTotalAmount = "6.55"
        val orderNumberOutput = OrderNumber()
        val expectedOrderNumber = orderNumberOutput.copy().increment().value

        val orderRepositorySlot = slot<Order>()
        val productIdSlot = mutableListOf<UUID>()

        every { orderRepository.save(capture(orderRepositorySlot)) } answers { orderRepositorySlot.captured }
        every { orderRepository.getLastOrderNumber() } returns orderNumberOutput.value
        every {
            productRepository.findById(capture(productIdSlot))
        } returns
            createProduct().copy(id = UUID.randomUUID()) andThen
            createProduct().copy(id = UUID.randomUUID()) andThen
            createProduct().copy(id = UUID.randomUUID())

        val output = useCase.execute(input)

        assertEquals(expectedOrderNumber, output.orderNumber)
        assertEquals(input.document, orderRepositorySlot.captured.document.value)
        assertEquals(input.items.size, orderRepositorySlot.captured.items.size)
        assertNull(orderRepositorySlot.captured.coupon)
        assertEquals(expectedTotalAmount, output.totalAmount.toString())
        assertEquals(expectedTotalAmount, orderRepositorySlot.captured.totalAmount.toString())

        verify {
            orderRepository.save(orderRepositorySlot.captured)
            productRepository.findById(capture(productIdSlot))
            orderRepository.getLastOrderNumber()
        }
    }

    @Test
    fun `should create an order when has a valid input with valid coupon`() {
        val input = createCreateOrderWithCouponInput()
        val orderNumberOutput = OrderNumber()

        val expectedOrderNumber = orderNumberOutput.copy().increment().value
        val expectedTotalAmount = "5.71"
        val expectedCoupon = createCoupon()

        val orderRepositorySlot = slot<Order>()
        val couponSlot = slot<String>()
        val productIdSlot = mutableListOf<UUID>()

        every { orderRepository.save(capture(orderRepositorySlot)) } answers { orderRepositorySlot.captured }
        every { couponRepository.findByCode(capture(couponSlot)) } returns expectedCoupon
        every { orderRepository.getLastOrderNumber() } returns orderNumberOutput.value
        every {
            productRepository.findById(capture(productIdSlot))
        } returns
            createProduct().copy(id = UUID.randomUUID()) andThen
            createProduct().copy(id = UUID.randomUUID()) andThen
            createProduct().copy(id = UUID.randomUUID())

        val output = useCase.execute(input)

        assertEquals(expectedOrderNumber, output.orderNumber)
        assertEquals(input.document, orderRepositorySlot.captured.document.value)
        assertEquals(input.items.size, orderRepositorySlot.captured.items.size)
        assertEquals(expectedCoupon.code, orderRepositorySlot.captured.coupon?.code)
        assertEquals(expectedTotalAmount, orderRepositorySlot.captured.totalAmount.toString())
        assertEquals(input.coupon, couponSlot.captured)
        assertEquals(expectedTotalAmount, output.totalAmount.toString())

        verify {
            orderRepository.save(orderRepositorySlot.captured)
            couponRepository.findByCode(couponSlot.captured)
            productRepository.findById(capture(productIdSlot))
            orderRepository.getLastOrderNumber()
        }
    }

    @Test
    fun `should throw exception when has a invalid coupon`() {
        val input = createCreateOrderWithCouponInput()
        val couponSlot = slot<String>()
        val orderNumberOutput = OrderNumber()

        every {
            productRepository.findById(any())
        } returns
            createProduct().copy(id = UUID.randomUUID()) andThen
            createProduct().copy(id = UUID.randomUUID()) andThen
            createProduct().copy(id = UUID.randomUUID())
        every { couponRepository.findByCode(capture(couponSlot)) } throws IllegalArgumentException()
        every { orderRepository.getLastOrderNumber() } returns orderNumberOutput.value

        assertThrows<IllegalArgumentException> { useCase.execute(input) }.run {
            assertEquals(input.coupon, couponSlot.captured)
        }

        verify {
            productRepository.findById(any())
            couponRepository.findByCode(couponSlot.captured)
            orderRepository.getLastOrderNumber()
        }
    }

    @Test
    fun `should throw exception when has a expired coupon`() {
        val input = createCreateOrderWithCouponInput()
        val orderNumberOutput = OrderNumber()
        val expiredCoupon = createExpiredCoupon()
        val couponSlot = slot<String>()

        every {
            productRepository.findById(any())
        } returns
            createProduct().copy(id = UUID.randomUUID()) andThen
            createProduct().copy(id = UUID.randomUUID()) andThen
            createProduct().copy(id = UUID.randomUUID())
        every { couponRepository.findByCode(capture(couponSlot)) } returns expiredCoupon
        every { orderRepository.getLastOrderNumber() } returns orderNumberOutput.value

        assertThrows<IllegalArgumentException> { useCase.execute(input) }.run {
            assertEquals(this.message, "Expired coupon")
            assertEquals(input.coupon, couponSlot.captured)
        }

        verify {
            productRepository.findById(any())
            couponRepository.findByCode(couponSlot.captured)
            orderRepository.getLastOrderNumber()
        }
    }
}
