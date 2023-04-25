package com.montebruni.sales.infra.repository.memoryrepository.model

import java.util.UUID

data class ProductMemoryRepositoryModel(
    val id: UUID,
    val description: String,
    val height: Double,
    val width: Double,
    val length: Double,
    val weight: Double
)
