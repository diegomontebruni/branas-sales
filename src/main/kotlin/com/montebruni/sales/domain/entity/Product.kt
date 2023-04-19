package com.montebruni.sales.domain.entity

import com.montebruni.sales.domain.valueobjects.PositiveDouble
import java.util.UUID

data class Product(
    val id: UUID,
    val description: String,
    val height: PositiveDouble,
    val width: PositiveDouble,
    val length: PositiveDouble,
    val weight: PositiveDouble
)
