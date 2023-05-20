package com.montebruni.sales.resource.rest

import com.montebruni.sales.application.usecase.CreateOrder
import com.montebruni.sales.application.usecase.FindOrderByOrderNumber
import com.montebruni.sales.application.usecase.GetAllOrders
import com.montebruni.sales.resource.rest.request.CreateCheckoutRequest
import com.montebruni.sales.resource.rest.request.toCreateOrderInput
import com.montebruni.sales.resource.rest.response.CreateCheckoutResponse
import com.montebruni.sales.resource.rest.response.OrderResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/orders")
class OrderController(
    private val createOrder: CreateOrder,
    private val findOrderByOrderNumber: FindOrderByOrderNumber,
    private val getAllOrders: GetAllOrders
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
        summary = "Get the order by order number.",
        description = "Get the saved order with an order number",
        tags = ["Orders"]
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Order is retrieve successfully"),
        ]
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{orderNumber}")
    fun getByOrderNumber(@PathVariable orderNumber: String) : OrderResponse =
        findOrderByOrderNumber.execute(orderNumber).let {
            OrderResponse(
                id = it.id,
                orderNumber = it.orderNumber,
                document = it.document,
                totalAmount = it.totalAmount,
                items = it.items.map { item -> OrderResponse.ItemResponse(
                    id = item.id,
                    productId = item.productId,
                    quantity = item.quantity
                ) }
            )
        }

    @Operation(
        summary = "Get orders",
        description = "Get the saved orders",
        tags = ["Orders"]
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Order is retrieve successfully"),
        ]
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    fun getOrders(): List<OrderResponse> = getAllOrders.execute().map {
            OrderResponse(
                id = it.id,
                orderNumber = it.orderNumber,
                document = it.document,
                totalAmount = it.totalAmount,
                items = it.items.map { item -> OrderResponse.ItemResponse(
                    id = item.id,
                    productId = item.productId,
                    quantity = item.quantity
                ) }
            )
        }
}
