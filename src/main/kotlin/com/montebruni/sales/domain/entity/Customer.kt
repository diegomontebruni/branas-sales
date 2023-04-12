package com.montebruni.sales.domain.entity

import com.montebruni.sales.domain.valueobjects.Document
import java.util.UUID

data class Customer(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val document: Document
)
