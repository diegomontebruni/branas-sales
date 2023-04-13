package com.montebruni.sales.infra.repository.memoryrepository.port

import com.montebruni.sales.infra.repository.memoryrepository.model.OrderMemoryRepositoryModel

interface OrderMemoryRepository {
    fun save(order: OrderMemoryRepositoryModel): OrderMemoryRepositoryModel
}
