package com.montebruni.sales.resource.rest.request

import com.montebruni.sales.usecase.input.CreateOrderInput
import java.util.*

data class CreateOrderRequest(
    val document: String,
    val items: List<ItemRequest>,
    val coupon: String? = null
) {
    data class ItemRequest(
        val productId: UUID,
        val price: Double,
        val quantity: Int
    )
}

fun CreateOrderRequest.toCreateOrderInput() = CreateOrderInput(
    document = document,
    items = items.map { CreateOrderInput.ItemInput(
        productId = it.productId,
        price = it.price,
        quantity = it.quantity
    ) },
    coupon = coupon
)
