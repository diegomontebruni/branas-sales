package com.montebruni.sales.domain.entity.freightCalculator.handle

import com.montebruni.sales.domain.entity.freightCalculator.FreightCalculatorHandler
import com.montebruni.sales.domain.entity.freightCalculator.input.FreightCalculatorInput
import org.springframework.stereotype.Component

@Component
class DefaultFreightCalculator : FreightCalculatorHandler {

    private val defaultFreightValue = 10.0
    override fun calculate(input: FreightCalculatorInput): Double =
        if (input.calculatedValue <= defaultFreightValue) defaultFreightValue else input.calculatedValue
}
