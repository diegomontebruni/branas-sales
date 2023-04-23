package com.montebruni.sales.domain.entity.freightCalculator.input

data class FreightCalculatorInput(
    val height: Double,
    val width: Double,
    val length: Double,
    val weight: Double,
    var calculatedValue: Double = 0.0
)
