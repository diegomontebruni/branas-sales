package com.montebruni.sales.domain.entity

import com.montebruni.sales.domain.valueobjects.Amount
import com.montebruni.sales.domain.valueobjects.Document
import com.montebruni.sales.extensions.toAmount
import java.util.UUID

data class Order(
    val id: UUID,
    val document: Document,
    var totalAmount: Amount = Amount(),
    val items: List<OrderItem> = emptyList(),
    val coupon: Coupon? = null,
) {

    init {
        if (hasDuplicatedItems()) throw IllegalArgumentException("Has duplicated items on list")
        calculateTotal()
    }

    companion object {
        fun generateId(): UUID = UUID.randomUUID()
    }

    private fun hasDuplicatedItems(): Boolean = items.groupingBy { it.product.id }.eachCount().any { it.value > 1}

    private fun calculateTotal() {
        totalAmount = items.sumOf { it.calculateTotalAmount().value }.toAmount().let {
            coupon?.calculateDiscount(it) ?: it
        }
    }
}
