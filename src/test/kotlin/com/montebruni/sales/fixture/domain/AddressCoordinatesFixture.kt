package com.montebruni.sales.fixture.domain

import com.montebruni.sales.application.domain.entity.AddressCoordinates
import java.math.BigDecimal

fun createAddressCoordinate() = AddressCoordinates(
    cep = "12312333",
    latitude = BigDecimal(10),
    longitude = BigDecimal(10)
)
