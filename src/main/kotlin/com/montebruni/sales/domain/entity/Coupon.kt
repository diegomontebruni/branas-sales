package com.montebruni.sales.domain.entity

import com.montebruni.sales.domain.valueobjects.Amount
import java.time.Instant

data class Coupon(
    val code: String,
    val percentage: Int,
    val expirationAt: Instant
) {

    init {
        if (percentage < 0 || percentage > 100) throw IllegalArgumentException("Invalid percentage")
    }

    fun calculateDiscount(totalAmount: Amount): Amount = totalAmount.percentage(percentage)
    fun isValid(): Boolean = expirationAt.isAfter(Instant.now())
}
