package com.montebruni.sales.fixture.domain.freightCalculator

import com.montebruni.sales.domain.entity.freightCalculator.input.FreightCalculatorInput

fun createFreightCalculatorInput() = FreightCalculatorInput(
    width = 20.0,
    height = 15.0,
    length = 10.0,
    weight = 1.0
)
