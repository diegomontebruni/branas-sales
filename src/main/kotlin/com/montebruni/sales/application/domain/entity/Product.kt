package com.montebruni.sales.application.domain.entity

import com.montebruni.sales.application.domain.valueobjects.Amount
import java.util.UUID

data class Product(
    val id: UUID,
    val description: String,
    val price: Amount,
)
