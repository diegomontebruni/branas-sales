package com.montebruni.sales.domain.entity.freightCalculator

import com.montebruni.sales.domain.entity.freightCalculator.handle.VolumeFreightCalculator
import com.montebruni.sales.domain.entity.freightCalculator.input.FreightCalculatorInput
import com.montebruni.sales.extensions.toDecimal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FreightCalculator {

    @Autowired private lateinit var handler: VolumeFreightCalculator

    fun calculate(input: FreightCalculatorInput): Double = handler.calculate(input).toDecimal()
}
