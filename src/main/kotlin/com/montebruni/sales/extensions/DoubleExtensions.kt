package com.montebruni.sales.extensions

import java.math.BigDecimal
import java.math.RoundingMode

fun Double.toMeters() = this / 100
fun Double.toDecimal(): Double = BigDecimal(this).setScale(2, RoundingMode.DOWN).toDouble()
