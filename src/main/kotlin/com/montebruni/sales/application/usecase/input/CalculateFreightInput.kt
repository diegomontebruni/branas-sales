package com.montebruni.sales.application.usecase.input

import com.montebruni.sales.application.domain.valueobjects.PositiveDouble

data class CalculateFreightInput(
    val items: List<Item>
) {
    data class Item(
        val quantity: Int,
        val height: PositiveDouble,
        val width: PositiveDouble,
        val length: PositiveDouble,
        val weight: PositiveDouble
    )
}
