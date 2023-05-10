package com.montebruni.sales.application.domain.entity

import com.montebruni.sales.application.domain.valueobjects.Amount
import java.time.Instant
import java.util.UUID

data class Coupon(
    val id: UUID,
    val code: String,
    val percentage: Int,
    val expirationAt: Instant
) {

    init {
        if (percentage < 0 || percentage > 100) throw IllegalArgumentException("Invalid percentage")
    }

    fun calculateDiscount(totalAmount: Amount): Amount = totalAmount.percentage(percentage)
    fun isValid(): Boolean = expirationAt.isAfter(Instant.now())
    fun throwIfExpired(): Coupon = if (!isValid()) throw IllegalArgumentException("Expired coupon") else this
}
