package com.montebruni.sales.application.domain.entity.freightCalculator

import java.math.BigDecimal

data class FreightCalculatorInput(
    val height: Double,
    val width: Double,
    val length: Double,
    val weight: Double,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    var calculatedValue: Double = 0.0
)
