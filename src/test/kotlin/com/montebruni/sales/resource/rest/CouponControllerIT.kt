package com.montebruni.sales.resource.rest

import com.montebruni.sales.application.usecase.ValidateCoupon
import com.montebruni.sales.application.usecase.input.ValidateCouponInput
import com.montebruni.sales.common.BaseRestIT
import com.ninjasquad.springmockk.MockkBean
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [CouponController::class])
class CouponControllerIT : BaseRestIT() {

    @MockkBean
    private lateinit var validateCoupon: ValidateCoupon

    private val baseUrl = "/v1/coupons"

    @AfterEach
    internal fun tearDown() {
        confirmVerified(validateCoupon)
    }

    private val couponCode = "1234"

    @Test
    fun `should return output with true when has a valid coupon code`() {
        val expectedOutput = true

        val useCaseSlot = slot<ValidateCouponInput>()

        every { validateCoupon.execute(capture(useCaseSlot)) } returns expectedOutput

        mockMvc.perform(
            get("$baseUrl/$couponCode/validate")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().is2xxSuccessful)
            .andExpect(MockMvcResultMatchers.jsonPath("is_valid").value(expectedOutput))
            .run {
                assertEquals(couponCode, useCaseSlot.captured.code)
            }

        verify { validateCoupon.execute(useCaseSlot.captured) }
    }
}
