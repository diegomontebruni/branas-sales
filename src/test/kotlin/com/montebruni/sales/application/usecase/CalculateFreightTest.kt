package com.montebruni.sales.application.usecase

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.application.domain.entity.Freight
import com.montebruni.sales.application.domain.port.AddressCoordinatesRepository
import com.montebruni.sales.application.domain.port.FreightCalculator
import com.montebruni.sales.fixture.domain.createAddressCoordinate
import com.montebruni.sales.fixture.usecase.createCalculateFreightInput
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class CalculateFreightTest(
    @MockK private val freightCalculator: FreightCalculator,
    @MockK private val addressCoordinatesRepository: AddressCoordinatesRepository
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
        val fromAddressCoordinates = createAddressCoordinate()
        val toAddressCoordinates = createAddressCoordinate()
            .copy(cep = "89783627", latitude = BigDecimal(609), longitude = BigDecimal(199))

        val freightSlot = mutableListOf<Freight>()
        val addressCoordinateSlot = mutableListOf<String>()

        every { freightCalculator.calculate(capture(freightSlot)) } returns
            camFreight andThen guitarFreight andThen refrigeratorFreight
        every { addressCoordinatesRepository.findByCep(capture(addressCoordinateSlot)) } returns
            fromAddressCoordinates andThen toAddressCoordinates

        val calculatedFreight = useCase.execute(input)

        assertEquals(expectedTotalValue, calculatedFreight)
        assertEquals(input.fromCep, addressCoordinateSlot.first())
        assertEquals(input.toCep, addressCoordinateSlot.last())

        freightSlot.forEach { verify { freightCalculator.calculate(it) } }
        addressCoordinateSlot.forEach { verify { addressCoordinatesRepository.findByCep(it) } }
    }

    @Test
    fun `should throw exception when has a invalid cep`() {
        val input = createCalculateFreightInput()

        every { addressCoordinatesRepository.findByCep(input.fromCep) } throws IllegalArgumentException()

        assertThrows<IllegalArgumentException> { useCase.execute(input) }

        verify { addressCoordinatesRepository.findByCep(input.fromCep) }
    }
}
