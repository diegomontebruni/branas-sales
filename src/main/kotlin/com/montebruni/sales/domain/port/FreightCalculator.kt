package com.montebruni.sales.domain.port

import com.montebruni.sales.domain.entity.Freight

interface FreightCalculator {
    fun calculate(input: Freight): Double
}
