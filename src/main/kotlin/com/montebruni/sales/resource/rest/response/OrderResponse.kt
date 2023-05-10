package com.montebruni.sales.resource.rest.response

import java.util.*

data class OrderResponse(
    val id: UUID,
    val orderNumber: String,
    val document: String,
    val totalAmount: Double,
    val items: List<ItemResponse>,
    val coupon: String? = null
) {
    data class ItemResponse(
        val id: UUID,
        val productId: UUID,
        val price: Double,
        val quantity: Int
    )
}
