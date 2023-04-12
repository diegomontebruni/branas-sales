package com.montebruni.sales.domain.entity

import com.montebruni.sales.domain.valueobjects.Amount

data class Coupon(
    val code: String,
    val percentage: Long,
) {

    init {
        if (percentage < 0 || percentage > 100) throw Exception("Invalid percentage")
    }

    fun calculateDiscount(totalAmount: Amount): Amount = totalAmount.percentage(percentage)
}
