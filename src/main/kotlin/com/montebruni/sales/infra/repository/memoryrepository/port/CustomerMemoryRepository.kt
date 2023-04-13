package com.montebruni.sales.infra.repository.memoryrepository.port

import com.montebruni.sales.infra.repository.memoryrepository.model.CustomerMemoryRepositoryModel
import java.util.*

interface CustomerMemoryRepository {
    fun findByDocument(document: String): CustomerMemoryRepositoryModel?
    fun findById(id: UUID): CustomerMemoryRepositoryModel?
}
