package com.montebruni.sales.infra.repository.memoryrepository.port

import com.montebruni.sales.infra.repository.memoryrepository.model.OrderProductMemoryRepositoryModel
import java.util.*

interface OrderProductMemoryRepository {

    fun findById(id: UUID): OrderProductMemoryRepositoryModel?
}
