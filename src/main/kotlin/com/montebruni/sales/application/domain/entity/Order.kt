package com.montebruni.sales.application.domain.entity

import com.montebruni.sales.application.domain.valueobjects.Amount
import com.montebruni.sales.application.domain.valueobjects.Document
import com.montebruni.sales.application.domain.valueobjects.OrderNumber
import com.montebruni.sales.extensions.toAmount
import java.util.UUID

data class Order(
    val id: UUID,
    val orderNumber: OrderNumber,
    val document: Document,
    var totalAmount: Amount = Amount(),
    val items: List<Item> = emptyList(),
    val coupon: Coupon? = null,
) {

    init {
        require(hasDuplicatedItems().not()) { "Has duplicated items on list" }
        calculateTotal()
    }

    private fun hasDuplicatedItems(): Boolean = items.groupingBy { it.product.id }.eachCount().any { it.value > 1 }

    private fun calculateTotal() {
        totalAmount = items.sumOf { it.calculateTotalAmount().value }.toAmount().let {
            coupon?.calculateDiscount(it) ?: it
        }
    }

    companion object {
        fun generateId(): UUID = UUID.randomUUID()
    }
}
