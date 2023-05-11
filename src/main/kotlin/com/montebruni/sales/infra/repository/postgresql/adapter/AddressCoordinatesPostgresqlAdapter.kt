package com.montebruni.sales.infra.repository.postgresql.adapter

import com.montebruni.sales.application.domain.entity.AddressCoordinates
import com.montebruni.sales.application.domain.port.AddressCoordinatesRepository
import com.montebruni.sales.extensions.repository.postgresql.toAddressCoordinates
import com.montebruni.sales.infra.repository.postgresql.port.AddressCoordinatesPostgresqlRepository
import org.springframework.beans.factory.annotation.Autowired

class AddressCoordinatesPostgresqlAdapter(
    @Autowired private val addressCoordinatesRepository: AddressCoordinatesPostgresqlRepository
) : AddressCoordinatesRepository {

    override fun findByCep(cep: String): AddressCoordinates? = addressCoordinatesRepository.findByCep(cep)
        ?.toAddressCoordinates()
}
