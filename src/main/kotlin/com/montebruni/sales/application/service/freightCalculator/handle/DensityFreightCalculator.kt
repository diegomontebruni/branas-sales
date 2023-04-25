package com.montebruni.sales.application.service.freightCalculator.handle

import com.montebruni.sales.application.service.freightCalculator.FreightCalculatorInput
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
