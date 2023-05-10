package com.montebruni.sales.application.domain.entity.freightCalculator.handlers

import com.montebruni.sales.application.domain.entity.freightCalculator.FreightCalculatorInput
import org.springframework.stereotype.Component

@Component
class DefaultFreightCalculator : FreightCalculatorHandler {

    private val defaultFreightValue = 10.0
    override fun calculate(input: FreightCalculatorInput): Double =
        if (input.calculatedValue <= defaultFreightValue) defaultFreightValue else input.calculatedValue
}
