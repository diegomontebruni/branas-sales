package com.montebruni.sales.application.domain.entity

import com.montebruni.sales.application.domain.valueobjects.Amount
import com.montebruni.sales.application.domain.valueobjects.PositiveDouble
import java.util.UUID

data class Product(
    val id: UUID,
    val description: String,
    val height: PositiveDouble,
    val width: PositiveDouble,
    val length: PositiveDouble,
    val weight: PositiveDouble,
    val price: Amount,
)
