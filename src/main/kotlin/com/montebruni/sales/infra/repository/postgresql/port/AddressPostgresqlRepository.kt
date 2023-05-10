package com.montebruni.sales.infra.repository.postgresql.port

import com.montebruni.sales.infra.repository.postgresql.model.AddressPostgresqlModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AddressPostgresqlRepository : JpaRepository<AddressPostgresqlModel, UUID> {

    fun findByCep(cep: String): AddressPostgresqlModel?
}
