package com.montebruni.sales.domain.entity.freightCalculator.handle

import com.montebruni.sales.domain.entity.freightCalculator.FreightCalculatorHandler
import com.montebruni.sales.domain.entity.freightCalculator.input.FreightCalculatorInput
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
