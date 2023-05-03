package com.montebruni.sales.infra.repository.memoryrepository.port

import com.montebruni.sales.infra.repository.memoryrepository.model.OrderMemoryRepositoryModel

interface OrderMemoryRepository {
    fun save(order: OrderMemoryRepositoryModel): OrderMemoryRepositoryModel
    fun getLastOrderNumber(): String?
    fun findByOrderNumber(orderNumber: String): OrderMemoryRepositoryModel?
    fun getAllOrders(): List<OrderMemoryRepositoryModel>
}
