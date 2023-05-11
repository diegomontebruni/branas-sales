package com.montebruni.sales.application.domain.port

import com.montebruni.sales.application.domain.entity.AddressCoordinates

interface AddressCoordinatesRepository {

    fun findByCep(cep: String): AddressCoordinates?
}
