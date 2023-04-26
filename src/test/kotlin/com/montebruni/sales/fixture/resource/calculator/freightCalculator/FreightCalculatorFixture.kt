package com.montebruni.sales.fixture.resource.calculator.freightCalculator

import com.montebruni.sales.application.service.freightCalculator.FreightCalculatorInput

fun createFreightCalculatorInput() = FreightCalculatorInput(
    width = 20.0,
    height = 15.0,
    length = 10.0,
    weight = 1.0
)