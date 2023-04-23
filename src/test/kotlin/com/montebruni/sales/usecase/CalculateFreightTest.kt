package com.montebruni.sales.usecase

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.domain.entity.freightCalculator.FreightCalculator
import com.montebruni.sales.domain.entity.freightCalculator.input.FreightCalculatorInput
import com.montebruni.sales.fixture.usecase.createCalculateFreightInput
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks

class CalculateFreightTest(
    @MockK private val freightCalculator: FreightCalculator
) : UnitTests() {

    @InjectMocks
    private lateinit var useCase: CalculateFreight

    @Test
    fun `should calculate a freight when given 3 products`() {
        val input = createCalculateFreightInput()
        val expectedTotalValue = 440.0
        val camFreight = 10.0
        val guitarFreight = 30.0
        val refrigeratorFreight = 400.0

        val freightSlot = mutableListOf<FreightCalculatorInput>()

        every { freightCalculator.calculate(capture(freightSlot)) } returns
            camFreight andThen guitarFreight andThen refrigeratorFreight

        val calculatedFreight = useCase.execute(input)

        assertEquals(expectedTotalValue, calculatedFreight)

        freightSlot.forEach { verify { freightCalculator.calculate(it) } }
    }
}
