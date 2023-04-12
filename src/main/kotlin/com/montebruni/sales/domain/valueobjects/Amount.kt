package com.montebruni.sales.domain.valueobjects

import com.montebruni.sales.extensions.toAmount
import java.math.BigDecimal

data class Amount(val value: BigDecimal = BigDecimal(0)) {

    constructor(value: String) : this(BigDecimal(value))
    constructor(value: Double) : this(BigDecimal(value.toString()))

    fun multiply(number: Int): Amount = Amount(value.multiply(BigDecimal(number)))
    fun percentage(percent: Long): Amount =
        value.subtract(value.multiply(BigDecimal(percent).divide(BigDecimal(100)))).toAmount()
}
