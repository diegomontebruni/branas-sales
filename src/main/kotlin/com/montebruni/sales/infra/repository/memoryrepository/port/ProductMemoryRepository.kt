package com.montebruni.sales.infra.repository.memoryrepository.port

import com.montebruni.sales.infra.repository.memoryrepository.model.ProductMemoryRepositoryModel
import java.util.UUID

interface ProductMemoryRepository {

    fun findById(id: UUID): ProductMemoryRepositoryModel?
}
