package com.montebruni.sales.extensions

import com.montebruni.sales.domain.valueobjects.PositiveDouble
import java.math.BigDecimal
import java.math.RoundingMode

fun Double.toMeters() = this / 100
fun Double.toDecimal(): Double = BigDecimal(this).setScale(2, RoundingMode.DOWN).toDouble()
fun Double.toPositiveDouble(): PositiveDouble = PositiveDouble(this)
