package com.montebruni.sales.resource.calculator.freightCalculator.handle

import com.montebruni.sales.resource.calculator.freightCalculator.FreightCalculatorHandler
import com.montebruni.sales.resource.calculator.freightCalculator.input.FreightCalculatorInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DistanceFreightCalculator : FreightCalculatorHandler {

    @Autowired
    private lateinit var nextHandle: DefaultFreightCalculator

    private val defaultDistance = 1000
    override fun calculate(input: FreightCalculatorInput): Double =
        (defaultDistance * input.calculatedValue).let { nextHandle.calculate(input.copy(calculatedValue = it)) }
}
