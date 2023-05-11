package com.montebruni.sales.fixture.infra.repository.postgresql

import com.montebruni.sales.infra.repository.postgresql.model.AddressCoordinatesPostgresqlModel
import java.math.BigDecimal
import java.util.*

fun createAddressCoordinatesPostgresqlModel() = AddressCoordinatesPostgresqlModel(
    id = UUID.randomUUID(),
    cep = "12312312",
    latitude = BigDecimal(-23.4947731),
    longitude = BigDecimal(-46.6163366)
)
