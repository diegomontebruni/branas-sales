package com.montebruni.sales.infra.repository.memoryrepository.port

import com.montebruni.sales.infra.repository.memoryrepository.model.OrderItemMemoryRepositoryModel
import java.util.*

interface OrderItemMemoryRepository {
    fun save(orderItemModel: OrderItemMemoryRepositoryModel) : OrderItemMemoryRepositoryModel
    fun findById(id: UUID): OrderItemMemoryRepositoryModel?
    fun findByOrderId(orderId: UUID): List<OrderItemMemoryRepositoryModel>
}
