package com.montebruni.sales.domain.entity

import com.montebruni.sales.domain.valueobjects.Amount

data class Product(
    val description: String,
    val amount: Amount,
    val quantity: Int
) {

    fun calculateTotalAmount(): Amount = amount.multiply(quantity)
}
