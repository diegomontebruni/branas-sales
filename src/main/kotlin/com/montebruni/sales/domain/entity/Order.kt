package com.montebruni.sales.domain.entity

import com.montebruni.sales.domain.valueobjects.Amount
import com.montebruni.sales.extensions.toAmount
import java.util.UUID

data class Order(
    val id: UUID = UUID.randomUUID(),
    val customerId: UUID,
    var totalAmount: Amount = Amount(),
    val products: MutableList<Product> = mutableListOf(),
    val coupon: Coupon? = null,
) {

    init {
        calculateTotal()
    }

    fun addProduct(product: Product) {
        products.add(product)
        calculateTotal()
    }

    private fun calculateTotal() {
        totalAmount = products.sumOf { it.calculateTotalAmount().value }.toAmount().let {
            coupon?.calculateDiscount(it) ?: it
        }
    }
}
