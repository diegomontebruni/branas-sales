package com.montebruni.sales.resource.calculator.freightCalculator.handle

import com.montebruni.sales.resource.calculator.freightCalculator.FreightCalculatorHandler
import com.montebruni.sales.resource.calculator.freightCalculator.input.FreightCalculatorInput
import org.springframework.stereotype.Component

@Component
class DefaultFreightCalculator : FreightCalculatorHandler {

    private val defaultFreightValue = 10.0
    override fun calculate(input: FreightCalculatorInput): Double =
        if (input.calculatedValue <= defaultFreightValue) defaultFreightValue else input.calculatedValue
}
