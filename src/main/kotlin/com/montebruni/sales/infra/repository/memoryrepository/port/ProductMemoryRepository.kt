package com.montebruni.sales.infra.repository.memoryrepository.port

import com.montebruni.sales.infra.repository.memoryrepository.model.ProductMemoryRepositoryModel
import java.util.*

interface ProductMemoryRepository {

    fun findById(id: UUID): ProductMemoryRepositoryModel?
}
