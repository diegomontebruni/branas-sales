package com.montebruni.sales.resource.rest

import com.montebruni.sales.resource.rest.request.CalculateFreightRequest
import com.montebruni.sales.resource.rest.request.CreateCheckoutRequest
import com.montebruni.sales.resource.rest.request.toCalculateFreightInput
import com.montebruni.sales.resource.rest.request.toCreateOrderInput
import com.montebruni.sales.resource.rest.response.CreateCheckoutResponse
import com.montebruni.sales.application.usecase.CalculateFreight
import com.montebruni.sales.application.usecase.CreateOrder
import com.montebruni.sales.resource.rest.response.CalculateFreightResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/orders")
class OrderController(
    private val createOrder: CreateOrder,
    private val calculateFreight: CalculateFreight
) {

    @Operation(
        summary = "Checkout of the order",
        description = "When the order is created successfully, a 200 status code is returned by the API",
        tags = ["Orders"]
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "The checkout process is successful."),
        ]
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/checkout")
    fun checkout(@RequestBody body: CreateCheckoutRequest): CreateCheckoutResponse =
        createOrder.execute(body.toCreateOrderInput()).let {
            CreateCheckoutResponse(
                orderNumber = it.orderNumber,
                totalAmount = it.totalAmount
            )
        }

    @Operation(
        summary = "Calculate the shipping cost of the order based on items dimensions.",
        description = "Calculate the total shipping cost for the items.",
        tags = ["Orders"]
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "The checkout process is successful."),
        ]
    )
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/simulate-freight")
    fun calculateFreight(@RequestBody body: CalculateFreightRequest): CalculateFreightResponse =
        CalculateFreightResponse(
            freightAmount = calculateFreight.execute(body.toCalculateFreightInput())
        )
}
