package com.montebruni.sales.application.domain.entity.freightCalculator

data class FreightCalculatorInput(
    val height: Double,
    val width: Double,
    val length: Double,
    val weight: Double,
    var calculatedValue: Double = 0.0
)
