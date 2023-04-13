package com.montebruni.sales.infra.repository.memoryrepository.model

import java.util.*

data class ProductMemoryRepositoryModel(
    val id: UUID,
    val description: String,
    val price: Double,
    val quantity: Int
)
