package com.montebruni.sales.resource.rest.request

import com.montebruni.sales.usecase.input.CreateOrderInput

data class CreateOrderRequest(
    val document: String,
    val items: List<ItemRequest>,
    val coupon: String? = null
) {
    data class ItemRequest(
        val description: String,
        val price: Double,
        val quantity: Int
    )
}

fun CreateOrderRequest.toCreateOrderInput() = CreateOrderInput(
    document = document,
    items = items.map { CreateOrderInput.ItemInput(
        description = it.description,
        price = it.price,
        quantity = it.quantity
    ) },
    coupon = coupon
)
