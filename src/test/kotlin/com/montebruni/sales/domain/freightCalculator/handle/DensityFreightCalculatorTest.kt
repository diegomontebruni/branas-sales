package com.montebruni.sales.domain.freightCalculator.handle

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.domain.entity.freightCalculator.handle.DensityFreightCalculator
import com.montebruni.sales.domain.entity.freightCalculator.handle.DistanceFreightCalculator
import com.montebruni.sales.domain.entity.freightCalculator.input.FreightCalculatorInput
import com.montebruni.sales.fixture.domain.freightCalculator.createFreightCalculatorInput
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DensityFreightCalculatorTest(
    @MockK private val nextHandle: DistanceFreightCalculator
) : UnitTests() {

    @InjectMockKs
    private lateinit var densityFreightCalculator: DensityFreightCalculator

    @Test
    fun `should calculate density when given a valid input`() {
        val input = createFreightCalculatorInput().copy(calculatedValue = 0.003)
        val expected = 0.009999999999999998

        val nextHandleSlot = slot<FreightCalculatorInput>()

        every { nextHandle.calculate(capture(nextHandleSlot)) } returns expected

        val result = densityFreightCalculator.calculate(input)

        assertEquals(expected, nextHandleSlot.captured.calculatedValue)
        assertEquals(expected, result)

        verify { nextHandle.calculate(nextHandleSlot.captured) }
    }
}
