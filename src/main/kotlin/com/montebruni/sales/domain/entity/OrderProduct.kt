package com.montebruni.sales.domain.entity

import com.montebruni.sales.domain.valueobjects.Amount
import java.util.*

data class OrderProduct(
    val id: UUID = UUID.randomUUID(),
    val productId: UUID,
    val price: Amount,
    val quantity: Int
) {

    init {
        if (quantity < 1) throw IllegalArgumentException("Invalid quantity")
    }

    fun calculateTotalAmount(): Amount = price.multiply(quantity)
}
