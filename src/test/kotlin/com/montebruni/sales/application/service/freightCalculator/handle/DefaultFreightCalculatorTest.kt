package com.montebruni.sales.application.service.freightCalculator.handle

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.application.service.freightCalculator.handle.DefaultFreightCalculator
import com.montebruni.sales.fixture.resource.calculator.freightCalculator.createFreightCalculatorInput
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DefaultFreightCalculatorTest : UnitTests() {

    @InjectMockKs
    private lateinit var defaultFreightCalculator: DefaultFreightCalculator

    @ParameterizedTest
    @ValueSource(doubles = [1.0, 2.0, 3.5, 9.0, 8.0, 9.999999999999998])
    fun `should calculate default freight value when value is less than default freight`(value: Double) {
        assertEquals(
            10.0,
            defaultFreightCalculator.calculate(createFreightCalculatorInput().copy(calculatedValue = value))
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [10.0000000001, 11.0, 100.0, 10000.0])
    fun `should calculate default freight value when value is greater than default freight`(value: Double) {
        assertEquals(
            value,
            defaultFreightCalculator.calculate(createFreightCalculatorInput().copy(calculatedValue = value))
        )
    }
}
