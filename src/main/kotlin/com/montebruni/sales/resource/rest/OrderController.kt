package com.montebruni.sales.resource.rest

import com.montebruni.sales.resource.rest.request.CreateOrderRequest
import com.montebruni.sales.resource.rest.request.toCreateOrderInput
import com.montebruni.sales.resource.rest.response.CreateOrderResponse
import com.montebruni.sales.usecase.CreateOrder
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/orders")
class OrderController(
    private val createOrder: CreateOrder
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createOrder(@RequestBody body: CreateOrderRequest): CreateOrderResponse =
        CreateOrderResponse(orderId = createOrder.execute(body.toCreateOrderInput()).orderId)
}
