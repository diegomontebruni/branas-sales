package com.montebruni.sales.application.usecase.output

import java.util.UUID

data class OrderOutput(
    val id: UUID,
    val orderNumber: String,
    val document: String,
    val totalAmount: Double,
    val items: List<ItemOutput>,
    val coupon: String? = null
) {
    data class ItemOutput(
        val id: UUID,
        val productId: UUID,
        val quantity: Int
    )
}
