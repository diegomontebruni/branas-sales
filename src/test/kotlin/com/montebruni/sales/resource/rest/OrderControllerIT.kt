package com.montebruni.sales.resource.rest

import com.montebruni.sales.common.rest.BaseRestIT
import com.montebruni.sales.fixture.resource.rest.createOrderRequest
import com.montebruni.sales.fixture.usecase.createCreateOrderOutput
import com.montebruni.sales.usecase.CreateOrder
import com.montebruni.sales.usecase.input.CreateOrderInput
import com.ninjasquad.springmockk.MockkBean
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@WebMvcTest(controllers = [OrderController::class])
class OrderControllerIT : BaseRestIT() {

    @MockkBean
    private lateinit var createOrder: CreateOrder

    private val baseUrl = "/v1/orders"

    @AfterEach
    internal fun tearDown() {
        confirmVerified(createOrder)
    }

    @Test
    fun `should return output when creation is successfully`() {
        val request = createOrderRequest()
        val expectedOutput = createCreateOrderOutput()

        val useCaseSlot = slot<CreateOrderInput>()

        every { createOrder.execute(capture(useCaseSlot)) } returns expectedOutput

        mockMvc.perform(
            post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        )
            .andExpect(status().is2xxSuccessful)
            .andExpect(jsonPath("order_id").value(expectedOutput.orderId.toString()))
            .andExpect(jsonPath("total_amount").value(expectedOutput.totalAmount.toString()))
            .run {
                assertEquals(request.document, useCaseSlot.captured.document)
                assertEquals(request.items.size, useCaseSlot.captured.items.size)
                assertNull(useCaseSlot.captured.coupon)
            }

        verify { createOrder.execute(useCaseSlot.captured) }
    }
}
