package com.montebruni.sales.infra.repository.memoryrepository.output

import java.util.*

data class ProductMemoryRepositoryOutput(
    val id: UUID,
    val description: String,
    val price: Double,
    val quantity: Int
)
