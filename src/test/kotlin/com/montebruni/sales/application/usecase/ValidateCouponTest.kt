package com.montebruni.sales.application.usecase

import com.montebruni.sales.application.domain.port.CouponRepository
import com.montebruni.sales.application.usecase.input.ValidateCouponInput
import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.domain.createCoupon
import com.montebruni.sales.fixture.domain.createExpiredCoupon
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ValidateCouponTest(
    @MockK private val couponRepository: CouponRepository
) : UnitTests() {

    @InjectMockKs
    private lateinit var validateCoupon: ValidateCoupon

    @AfterEach
    internal fun tearDown() {
        confirmVerified(couponRepository)
    }

    @Nested
    @DisplayName("Validate Coupon")
    inner class ValidateCouponTestCases {

        @Test
        fun `should return true if has a valid coupon`() {
            val code = "BR123"
            val couponOutput = createCoupon().copy(code = "BR123")

            val couponSlot = slot<String>()

            every { couponRepository.findByCode(capture(couponSlot)) } returns couponOutput

            val response = validateCoupon.execute(ValidateCouponInput(code))

            assertEquals(code, couponSlot.captured)
            assertTrue(response)

            verify { couponRepository.findByCode(couponSlot.captured) }
        }

        @Test
        fun `should return false if has an expired coupon`() {
            val code = "BR123"
            val couponOutput = createExpiredCoupon().copy(code = "BR123")

            val couponSlot = slot<String>()

            every { couponRepository.findByCode(capture(couponSlot)) } returns couponOutput

            val response = validateCoupon.execute(ValidateCouponInput(code))

            assertEquals(code, couponSlot.captured)
            assertFalse(response)

            verify { couponRepository.findByCode(couponSlot.captured) }
        }
    }
}
