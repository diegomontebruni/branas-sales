package com.montebruni.sales.application.domain.valueobjects

import java.time.Year

data class OrderNumber(var value: String? = null) {

    init {
        value = generateCode()
        if (!isValidCode()) throw IllegalArgumentException("Invalid code")
    }

    private fun isValidCode(): Boolean = value!!.matches("""^\d{4}\d{8}$""".toRegex())

    private fun generateCode(): String {
        val year = Year.now().value

        if (value.isNullOrEmpty()) return "${year}${0.toString().repeat(8)}"

        val lastOrderNumber = value!!.replace("^.*(?=.{8})".toRegex(), "")
        val increasedOrderNumber = lastOrderNumber.toLongOrNull()?.inc()
            ?: throw IllegalArgumentException("Invalid last order number")

        return "${year}${increasedOrderNumber.toString().padStart(8, '0')}"
    }
}
