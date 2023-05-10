package com.montebruni.sales.infra.repository.postgresql.repository

import com.montebruni.sales.common.DatabaseIT
import com.montebruni.sales.fixture.infra.repository.postgresql.createAddressPostgresqlModel
import com.montebruni.sales.infra.repository.postgresql.port.AddressPostgresqlRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class AddressPostgresqlRepositoryIT(
    @Autowired private val addressRepository: AddressPostgresqlRepository
) : DatabaseIT() {

    @Test
    fun `should find an address when given a valid cep`() {
        val address = createAddressPostgresqlModel().let { addressRepository.save(it) }

        val savedAddress = addressRepository.findByCep(address.cep)

        assertNotNull(savedAddress)
        assertEquals(address.id, savedAddress?.id)
        assertEquals(address.cep, savedAddress?.cep)
    }

    @Test
    fun `should return null when given an invalid cep`() {
        assertNull(addressRepository.findByCep("123"))
    }
}
