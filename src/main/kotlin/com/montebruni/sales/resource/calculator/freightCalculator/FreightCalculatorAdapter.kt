package com.montebruni.sales.resource.calculator.freightCalculator

import com.montebruni.sales.domain.entity.Freight
import com.montebruni.sales.resource.calculator.freightCalculator.handle.VolumeFreightCalculator
import com.montebruni.sales.resource.calculator.freightCalculator.input.FreightCalculatorInput
import com.montebruni.sales.domain.port.FreightCalculator
import com.montebruni.sales.extensions.toDecimal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FreightCalculatorAdapter : FreightCalculator {

    @Autowired private lateinit var handler: VolumeFreightCalculator

    override fun calculate(input: Freight): Double = handler.calculate(FreightCalculatorInput(
        height = input.height.value,
        width = input.width.value,
        length = input.length.value,
        weight = input.weight.value
    )).toDecimal()
}
