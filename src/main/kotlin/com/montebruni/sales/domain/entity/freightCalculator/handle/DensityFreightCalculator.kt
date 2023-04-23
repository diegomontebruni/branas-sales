package com.montebruni.sales.domain.entity.freightCalculator.handle

import com.montebruni.sales.domain.entity.freightCalculator.FreightCalculatorHandler
import com.montebruni.sales.domain.entity.freightCalculator.input.FreightCalculatorInput
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
