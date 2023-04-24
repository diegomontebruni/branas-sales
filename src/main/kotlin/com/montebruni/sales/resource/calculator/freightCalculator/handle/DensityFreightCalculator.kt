package com.montebruni.sales.resource.calculator.freightCalculator.handle

import com.montebruni.sales.resource.calculator.freightCalculator.FreightCalculatorHandler
import com.montebruni.sales.resource.calculator.freightCalculator.input.FreightCalculatorInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DensityFreightCalculator : FreightCalculatorHandler {

    @Autowired
    private lateinit var nextHandle: DistanceFreightCalculator

    override fun calculate(input: FreightCalculatorInput): Double =
        (input.calculatedValue * calculateDensity(input.weight, input.calculatedValue)).let {
            nextHandle.calculate(input.copy(calculatedValue = it))
        }

    private fun calculateDensity(weight: Double, volume: Double) = ((weight / volume) / 100)
}
