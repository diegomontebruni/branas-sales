package com.montebruni.sales.domain.entity.freightCalculator

import com.montebruni.sales.domain.entity.freightCalculator.input.FreightCalculatorInput

interface FreightCalculatorHandler {
    fun calculate(input: FreightCalculatorInput) : Double
}
