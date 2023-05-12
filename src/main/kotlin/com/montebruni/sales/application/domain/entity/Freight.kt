package com.montebruni.sales.application.domain.entity

import com.montebruni.sales.application.domain.valueobjects.PositiveDouble
import java.math.BigDecimal

data class Freight(
    val height: PositiveDouble,
    val width: PositiveDouble,
    val length: PositiveDouble,
    val weight: PositiveDouble,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val quantity: Int
)
