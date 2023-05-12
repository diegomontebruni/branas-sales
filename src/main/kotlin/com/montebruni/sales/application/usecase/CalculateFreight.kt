package com.montebruni.sales.application.usecase

import com.montebruni.sales.application.domain.entity.Freight
import com.montebruni.sales.application.domain.port.AddressCoordinatesRepository
import com.montebruni.sales.application.domain.port.FreightCalculator
import com.montebruni.sales.extensions.toPositiveDouble
import com.montebruni.sales.application.usecase.input.CalculateFreightInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CalculateFreight(
    @Autowired private val freightCalculator: FreightCalculator,
    @Autowired private val addressCoordinatesRepository: AddressCoordinatesRepository,
){
    fun execute(input: CalculateFreightInput): Double {
        val fromCoordinates = addressCoordinatesRepository.findByCep(input.fromCep)
            ?: throw IllegalArgumentException("Invalid from Cep")

        val toCoordinates = addressCoordinatesRepository.findByCep(input.toCep)
            ?: throw IllegalArgumentException("Invalid to Cep")

        return input.items.sumOf {
            freightCalculator.calculate(
                Freight(
                    height = it.height.value.toPositiveDouble(),
                    width = it.width.value.toPositiveDouble(),
                    length = it.length.value.toPositiveDouble(),
                    weight = it.weight.value.toPositiveDouble(),
                    quantity = it.quantity,
                    from = Freight.Coordinates(fromCoordinates.latitude, fromCoordinates.longitude),
                    to = Freight.Coordinates(toCoordinates.latitude, toCoordinates.longitude)
                )
            )
        }
    }
}
