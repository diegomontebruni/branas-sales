package com.montebruni.sales.fixture.usecase

import com.montebruni.sales.application.domain.valueobjects.PositiveDouble
import com.montebruni.sales.application.usecase.input.CalculateFreightInput

fun createCalculateFreightInput() = CalculateFreightInput(
    fromCep = "12312541",
    toCep = "90710923",
    listOf(
        CalculateFreightInput.Item(
            quantity = 1,
            height = PositiveDouble(20.0),
            width = PositiveDouble(15.0),
            length = PositiveDouble(10.0),
            weight = PositiveDouble(1.0)
        ),
        CalculateFreightInput.Item(
            quantity = 1,
            height = PositiveDouble(100.0),
            width = PositiveDouble(30.0),
            length = PositiveDouble(10.0),
            weight = PositiveDouble(3.0)
        ),
        CalculateFreightInput.Item(
            quantity = 1,
            height = PositiveDouble(200.0),
            width = PositiveDouble(100.0),
            length = PositiveDouble(50.0),
            weight = PositiveDouble(40.0)
        )
    )
)
