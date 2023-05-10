package com.montebruni.sales.application.domain.entity

import com.montebruni.sales.application.domain.valueobjects.Amount
import java.util.*

data class Item(
    val id: UUID = UUID.randomUUID(),
    val product: Product,
    val price: Amount,
    val quantity: Int
) {

    init {
        if (quantity < 1) throw IllegalArgumentException("Invalid quantity")
    }

    fun calculateTotalAmount(): Amount = price.multiply(quantity)
}
