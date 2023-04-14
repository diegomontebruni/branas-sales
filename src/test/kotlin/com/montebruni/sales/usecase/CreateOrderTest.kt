package com.montebruni.sales.usecase

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.domain.entity.Order
import com.montebruni.sales.domain.port.CouponRepository
import com.montebruni.sales.domain.port.OrderRepository
import com.montebruni.sales.fixture.createCoupon
import com.montebruni.sales.fixture.createOrder
import com.montebruni.sales.fixture.usecase.createCreateOrderInput
import com.montebruni.sales.fixture.usecase.createCreateOrderWithCouponInput
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
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
    @MockK private val couponRepository: CouponRepository
) : UnitTests() {

    @InjectMockKs
    private lateinit var useCase: CreateOrder

    @AfterEach
    internal fun tearDown() {
        confirmVerified(orderRepository, couponRepository)
    }

    @Test
    fun `should create an order when has a valid input without coupon`() {
        val input = createCreateOrderInput()
        val orderId = UUID.randomUUID()
        val expectedTotalAmount = "6.00"

        val orderRepositorySlot = slot<Order>()

        mockkStatic(UUID::class)
        every { UUID.randomUUID() } returns orderId
        every { orderRepository.save(capture(orderRepositorySlot)) } returns createOrder()

        val output = useCase.execute(input)

        assertEquals(orderId, orderRepositorySlot.captured.id)
        assertEquals(input.document, orderRepositorySlot.captured.document.value)
        assertEquals(input.products.size, orderRepositorySlot.captured.products.size)
        assertEquals(orderId, orderRepositorySlot.captured.products.first().orderId)
        assertNull(orderRepositorySlot.captured.coupon)
        assertEquals(orderId, output.orderId)
        assertEquals(expectedTotalAmount, orderRepositorySlot.captured.totalAmount.toString())

        verify {
            orderRepository.save(orderRepositorySlot.captured)
        }
    }

    @Test
    fun `should create an order when has a valid input with valid coupon`() {
        val input = createCreateOrderWithCouponInput()
        val expectedCoupon = createCoupon()
        val orderId = UUID.randomUUID()
        val expectedTotalAmount = "5.40"

        val orderRepositorySlot = slot<Order>()
        val couponSlot = slot<String>()

        mockkStatic(UUID::class)
        every { UUID.randomUUID() } returns orderId
        every { orderRepository.save(capture(orderRepositorySlot)) } returns createOrder()
        every { couponRepository.findByCode(capture(couponSlot)) } returns expectedCoupon

        val output = useCase.execute(input)

        assertEquals(orderId, output.orderId)
        assertEquals(orderId, orderRepositorySlot.captured.id)
        assertEquals(input.document, orderRepositorySlot.captured.document.value)
        assertEquals(input.products.size, orderRepositorySlot.captured.products.size)
        assertEquals(orderId, orderRepositorySlot.captured.products.first().orderId)
        assertEquals(expectedCoupon.code, orderRepositorySlot.captured.coupon?.code)
        assertEquals(expectedTotalAmount, orderRepositorySlot.captured.totalAmount.toString())
        assertEquals(input.coupon, couponSlot.captured)

        verify {
            orderRepository.save(orderRepositorySlot.captured)
            couponRepository.findByCode(couponSlot.captured)
        }
    }

    @Test
    fun `should throw exception when has a invalid coupon`() {
        val input = createCreateOrderWithCouponInput()
        val couponSlot = slot<String>()
        val orderId = UUID.randomUUID()

        mockkStatic(UUID::class)
        every { UUID.randomUUID() } returns orderId
        every { couponRepository.findByCode(capture(couponSlot)) } throws IllegalArgumentException()

        assertThrows<IllegalArgumentException> { useCase.execute(input) }.run {
            assertEquals(input.coupon, couponSlot.captured)
        }

        verify {
            couponRepository.findByCode(couponSlot.captured)
        }
    }
}
