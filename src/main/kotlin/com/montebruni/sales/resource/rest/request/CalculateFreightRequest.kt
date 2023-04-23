package com.montebruni.sales.resource.rest.request

import com.montebruni.sales.domain.valueobjects.PositiveDouble
import com.montebruni.sales.usecase.input.CalculateFreightInput

data class CalculateFreightRequest(
    val items: List<ItemRequest>
) {
    data class ItemRequest(
        val quantity: Int,
        val height: Double,
        val width: Double,
        val length: Double,
        val weight: Double
    )
}

fun CalculateFreightRequest.toCalculateFreightInput() = CalculateFreightInput(
    items = items.map {
        CalculateFreightInput.Item(
            quantity = it.quantity,
            height = PositiveDouble(it.height),
            width = PositiveDouble(it.width),
            length = PositiveDouble(it.length),
            weight = PositiveDouble(it.weight)
        )
    }
)
