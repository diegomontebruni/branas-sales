package com.montebruni.sales.application.service.freightCalculator.handle

import com.montebruni.sales.application.service.freightCalculator.FreightCalculatorInput

interface FreightCalculatorHandler {
    fun calculate(input: FreightCalculatorInput) : Double
}
