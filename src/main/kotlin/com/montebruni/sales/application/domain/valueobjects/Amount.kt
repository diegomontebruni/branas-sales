package com.montebruni.sales.application.domain.valueobjects

import com.montebruni.sales.extensions.toAmount
import java.math.BigDecimal
import java.math.RoundingMode

data class Amount(val value: BigDecimal = BigDecimal(0)) {

    init {
        require(value >= BigDecimal.ZERO) { "Value is lower than zero" }
    }

    constructor(value: String) : this(BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN))
    constructor(value: Double) : this(BigDecimal(value.toString()).setScale(2, RoundingMode.HALF_EVEN))

    fun multiply(number: Int): Amount = Amount(value.multiply(BigDecimal(number)))
    fun percentage(percent: Int): Amount =
        value.subtract(value.multiply(BigDecimal(percent).divide(BigDecimal(PERCENTAGE_MAX)))).toAmount()

    override fun toString(): String = value.toString()

    companion object {
        private const val PERCENTAGE_MAX = 100
    }
}
