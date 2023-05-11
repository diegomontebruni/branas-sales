package com.montebruni.sales.extensions.repository.postgresql

import com.montebruni.sales.application.domain.entity.AddressCoordinates
import com.montebruni.sales.infra.repository.postgresql.model.AddressCoordinatesPostgresqlModel

fun AddressCoordinatesPostgresqlModel.toAddressCoordinates() = AddressCoordinates(
    cep = cep,
    latitude = latitude,
    longitude = longitude
)
