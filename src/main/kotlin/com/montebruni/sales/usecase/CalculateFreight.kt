package com.montebruni.sales.usecase

import com.montebruni.sales.domain.entity.freightCalculator.FreightCalculator
import com.montebruni.sales.domain.entity.freightCalculator.input.FreightCalculatorInput
import com.montebruni.sales.usecase.input.CalculateFreightInput
import org.springframework.stereotype.Service

@Service
class CalculateFreight(
    private val freightCalculator: FreightCalculator
){
    fun execute(input: CalculateFreightInput): Double = input.items.sumOf {
        it.quantity * freightCalculator.calculate(
            FreightCalculatorInput(
                height = it.height.value,
                width = it.width.value,
                length = it.length.value,
                weight = it.weight.value
            )
        )
    }
}
