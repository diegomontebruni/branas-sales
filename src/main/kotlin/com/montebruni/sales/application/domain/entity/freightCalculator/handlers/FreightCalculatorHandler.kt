package com.montebruni.sales.application.domain.entity.freightCalculator.handlers

import com.montebruni.sales.application.domain.entity.freightCalculator.FreightCalculatorInput

interface FreightCalculatorHandler {
    fun calculate(input: FreightCalculatorInput) : Double
}
