package com.montebruni.sales.infra.repository.postgresql.port

import com.montebruni.sales.infra.repository.postgresql.model.AddressCoordinatesPostgresqlModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AddressCoordinatesPostgresqlRepository : JpaRepository<AddressCoordinatesPostgresqlModel, UUID> {

    fun findByCep(cep: String): AddressCoordinatesPostgresqlModel?
}
