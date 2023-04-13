package com.montebruni.sales.domain.entity

import com.montebruni.sales.domain.valueobjects.Amount
import java.util.UUID

data class Product(
    val id: UUID = UUID.randomUUID(),
    val description: String,
    val price: Amount,
    val quantity: Int
) {

    init {
        if (quantity < 1) throw IllegalArgumentException("Invalid quantity")
    }

    fun calculateTotalAmount(): Amount = price.multiply(quantity)
}
