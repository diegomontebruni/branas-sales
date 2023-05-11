package com.montebruni.sales.infra.repository.postgresql.adapter

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.infra.repository.postgresql.createAddressCoordinatesPostgresqlModel
import com.montebruni.sales.infra.repository.postgresql.port.AddressCoordinatesPostgresqlRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class AddressCoordinatesPostgresqlAdapterTest(
    @MockK private val addressCoordinatesRepository: AddressCoordinatesPostgresqlRepository
) : UnitTests() {

    @InjectMockKs
    private lateinit var addressCoordinatesAdapter: AddressCoordinatesPostgresqlAdapter

    @Test
    fun `should find address coordinates by cep`() {
        val addressModel = createAddressCoordinatesPostgresqlModel()

        every { addressCoordinatesRepository.findByCep(addressModel.cep) } returns addressModel

        val addressCoordinates = addressCoordinatesAdapter.findByCep(addressModel.cep)

        assertNotNull(addressCoordinates)
        assertEquals(addressModel.cep, addressCoordinates?.cep)
        assertEquals(addressModel.latitude, addressCoordinates?.latitude)
        assertEquals(addressModel.longitude, addressCoordinates?.longitude)

        verify { addressCoordinatesRepository.findByCep(addressModel.cep) }
    }

    @Test
    fun `should return null when try to find address coordinates by an fake cep`() {
        val fakeCep = "123"
        every { addressCoordinatesRepository.findByCep(fakeCep) } returns null

        val addressCoordinates = addressCoordinatesAdapter.findByCep(fakeCep)

        assertNull(addressCoordinates)
        verify { addressCoordinatesRepository.findByCep(fakeCep) }
    }
}
