package com.montebruni.sales.infra.repository.memoryrepository.port

import com.montebruni.sales.infra.repository.memoryrepository.model.OrderProductMemoryRepositoryModel
import java.util.*

interface OrderProductMemoryRepository {
    fun save(orderProductModel: OrderProductMemoryRepositoryModel) : OrderProductMemoryRepositoryModel
    fun findById(id: UUID): OrderProductMemoryRepositoryModel?
    fun findByOrderId(orderId: UUID): List<OrderProductMemoryRepositoryModel>
}
