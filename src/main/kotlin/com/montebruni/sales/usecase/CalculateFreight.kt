package com.montebruni.sales.usecase

import com.montebruni.sales.domain.entity.Freight
import com.montebruni.sales.domain.port.FreightCalculator
import com.montebruni.sales.extensions.toPositiveDouble
import com.montebruni.sales.usecase.input.CalculateFreightInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CalculateFreight(
    @Autowired private val freightCalculator: FreightCalculator
){
    fun execute(input: CalculateFreightInput): Double = input.items.sumOf {
        it.quantity * freightCalculator.calculate(
            Freight(
                height = it.height.value.toPositiveDouble(),
                width = it.width.value.toPositiveDouble(),
                length = it.length.value.toPositiveDouble(),
                weight = it.weight.value.toPositiveDouble()
            )
        )
    }
}
