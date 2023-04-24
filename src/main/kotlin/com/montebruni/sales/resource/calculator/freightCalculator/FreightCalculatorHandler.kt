package com.montebruni.sales.resource.calculator.freightCalculator

import com.montebruni.sales.resource.calculator.freightCalculator.input.FreightCalculatorInput

interface FreightCalculatorHandler {
    fun calculate(input: FreightCalculatorInput) : Double
}
