package com.montebruni.sales.application.domain.port

import com.montebruni.sales.application.domain.entity.Freight

interface FreightCalculator {
    fun calculate(input: Freight): Double
}
