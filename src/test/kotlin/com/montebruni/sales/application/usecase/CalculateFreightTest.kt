package com.montebruni.sales.application.usecase

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.application.domain.entity.Freight
import com.montebruni.sales.application.domain.port.FreightCalculator
import com.montebruni.sales.fixture.usecase.createCalculateFreightInput
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CalculateFreightTest(
    @MockK private val freightCalculator: FreightCalculator,
) : UnitTests() {

    @InjectMockKs
    private lateinit var useCase: CalculateFreight

    @Test
    fun `should calculate a freight when given 3 products`() {
        val input = createCalculateFreightInput()
        val expectedTotalValue = 440.0
        val camFreight = 10.0
        val guitarFreight = 30.0
        val refrigeratorFreight = 400.0

        val freightSlot = mutableListOf<Freight>()

        every { freightCalculator.calculate(capture(freightSlot)) } returns
            camFreight andThen guitarFreight andThen refrigeratorFreight

        val calculatedFreight = useCase.execute(input)

        assertEquals(expectedTotalValue, calculatedFreight)

        freightSlot.forEach { verify { freightCalculator.calculate(it) } }
    }
}
