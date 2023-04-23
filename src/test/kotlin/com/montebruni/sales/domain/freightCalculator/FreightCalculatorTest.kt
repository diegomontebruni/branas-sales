package com.montebruni.sales.domain.freightCalculator

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.domain.entity.freightCalculator.FreightCalculator
import com.montebruni.sales.domain.entity.freightCalculator.handle.VolumeFreightCalculator
import com.montebruni.sales.domain.entity.freightCalculator.input.FreightCalculatorInput
import com.montebruni.sales.fixture.domain.freightCalculator.createFreightCalculatorInput
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FreightCalculatorTest(
    @MockK private val handler: VolumeFreightCalculator
) : UnitTests() {

    @InjectMockKs
    private lateinit var freightCalculator: FreightCalculator

    @Test
    fun `should calculate a freight with 2 decimal places when given a valid input`() {
        val input = createFreightCalculatorInput()
        val expectedOutput = 10.0

        val handleSlot = slot<FreightCalculatorInput>()

        every { handler.calculate(capture(handleSlot)) } returns expectedOutput

        val response = freightCalculator.calculate(input)

        assertEquals(expectedOutput, response)

        verify { handler.calculate(handleSlot.captured) }
    }

    @Test
    fun `should calculate a freight with 2 decimal places when output has more than 2 decimal places`() {
        val input = createFreightCalculatorInput()
        val expectedHandleOutput = 10.99999
        val expectedOutput = 10.99

        val handleSlot = slot<FreightCalculatorInput>()

        every { handler.calculate(capture(handleSlot)) } returns expectedHandleOutput

        val response = freightCalculator.calculate(input)

        assertEquals(expectedOutput, response)

        verify { handler.calculate(handleSlot.captured) }
    }
}
