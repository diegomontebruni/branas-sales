package com.montebruni.sales.application.service.freightCalculator.handle

import com.montebruni.sales.application.service.freightCalculator.FreightCalculatorInput
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
