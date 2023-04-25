package com.montebruni.sales.application.usecase.input

import java.util.UUID

data class CreateOrderInput(
    val document: String,
    val items: List<ItemInput>,
    val coupon: String? = null
) {
    data class ItemInput(
        val productId: UUID,
        val price: Double,
        val quantity: Int
    )
}
