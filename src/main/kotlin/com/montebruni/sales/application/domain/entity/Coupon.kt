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

    companion object {
        private const val PERCENTAGE_MIN = 0
        private const val PERCENTAGE_MAX = 100
    }

    init {
        require(percentage in (PERCENTAGE_MIN + 1)..<PERCENTAGE_MAX) { "Invalid percentage" }
    }

    fun calculateDiscount(totalAmount: Amount): Amount = totalAmount.percentage(percentage)
    fun isValid(): Boolean = expirationAt.isAfter(Instant.now())
    fun throwIfExpired(): Coupon {
        require(isValid()) { "Expired coupon" }
        return this
    }
}
