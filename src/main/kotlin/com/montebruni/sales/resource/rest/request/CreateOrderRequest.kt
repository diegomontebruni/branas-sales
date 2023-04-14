package com.montebruni.sales.resource.rest.request

import com.montebruni.sales.usecase.input.CreateOrderInput

data class CreateOrderRequest(
    val document: String,
    val products: List<OrderProductRequest>,
    val coupon: String? = null
) {
    data class OrderProductRequest(
        val description: String,
        val price: Double,
        val quantity: Int
    )
}

fun CreateOrderRequest.toCreateOrderInput() = CreateOrderInput(
    document = document,
    products = products.map { CreateOrderInput.OrderProductInput(
        description = it.description,
        price = it.price,
        quantity = it.quantity
    ) },
    coupon = coupon
)
