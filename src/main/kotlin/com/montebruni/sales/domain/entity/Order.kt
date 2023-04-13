package com.montebruni.sales.domain.entity

import com.montebruni.sales.domain.valueobjects.Amount
import com.montebruni.sales.domain.valueobjects.Document
import com.montebruni.sales.extensions.toAmount
import java.util.UUID

data class Order(
    val id: UUID = UUID.randomUUID(),
    val document: Document,
    var totalAmount: Amount = Amount(),
    val products: List<OrderProduct> = emptyList(),
    val coupon: Coupon? = null,
) {

    init {
        calculateTotal()
    }

    private fun calculateTotal() {
        totalAmount = products.sumOf { it.calculateTotalAmount().value }.toAmount().let {
            coupon?.calculateDiscount(it) ?: it
        }
    }
}
