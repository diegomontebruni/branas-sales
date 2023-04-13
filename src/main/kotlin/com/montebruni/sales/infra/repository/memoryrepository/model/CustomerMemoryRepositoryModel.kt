package com.montebruni.sales.infra.repository.memoryrepository.model

import java.util.UUID

data class CustomerMemoryRepositoryModel(
    val id: UUID,
    val name: String,
    val document: String
)
