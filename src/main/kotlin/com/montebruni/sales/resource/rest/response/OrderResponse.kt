package com.montebruni.sales.resource.rest.response

import java.util.*

data class OrderResponse(
    val id: UUID,
    val orderNumber: String,
    val document: String,
    val totalAmount: Double,
    val items: List<OrderItemResponse>,
    val coupon: String? = null
) {
    data class OrderItemResponse(
        val id: UUID,
        val productId: UUID,
        val price: Double,
        val quantity: Int
    )
}
