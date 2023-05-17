package com.montebruni.sales.fixture.resource.calculator.freightCalculator

import com.montebruni.sales.application.domain.entity.freightCalculator.FreightCalculatorInput
import java.math.BigDecimal

fun createFreightCalculatorInput() = FreightCalculatorInput(
    width = 20.0,
    height = 15.0,
    length = 10.0,
    weight = 1.0,
    from = FreightCalculatorInput.Coordinates(latitude = BigDecimal(123), longitude = BigDecimal(123)),
    to = FreightCalculatorInput.Coordinates(latitude = BigDecimal(456), longitude = BigDecimal(456))
)
