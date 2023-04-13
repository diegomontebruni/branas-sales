package com.montebruni.sales.infra.repository.memoryrepository.output

import java.util.UUID

data class CustomerMemoryRepositoryOutput(
    val id: UUID,
    val name: String,
    val document: String
)
