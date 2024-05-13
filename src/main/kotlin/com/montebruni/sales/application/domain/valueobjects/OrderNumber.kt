package com.montebruni.sales.application.domain.valueobjects

import java.time.Year

data class OrderNumber(var value: String) {

    constructor() : this(generateCode())

    init {
        require(isValidCode()) { "Invalid code" }
    }

    fun increment(): OrderNumber {
        val lastOrderNumber = value.replace("^.*(?=.{8})".toRegex(), "")
        val increasedOrderNumber = lastOrderNumber.toLongOrNull()?.inc()
            ?: throw IllegalArgumentException("Invalid last order number")

        value = "${Year.now().value}${increasedOrderNumber.toString().padStart(8, '0')}"

        return this
    }

    private fun isValidCode(): Boolean = value.matches("""^\d{4}\d{8}$""".toRegex())

    companion object {

        private fun generateCode(): String = "${Year.now().value}${0.toString().repeat(8)}"
    }
}
