package com.montebruni.sales.application.domain.entity

import com.montebruni.sales.application.domain.valueobjects.Amount
import java.util.*

data class Item(
    val id: UUID = UUID.randomUUID(),
    val product: Product,
    val quantity: Int
) {

    init {
        require(quantity > 0) { "Invalid quantity" }
    }

    fun calculateTotalAmount(): Amount = product.price.multiply(quantity)
}
