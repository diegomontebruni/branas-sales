package com.montebruni.sales.infra.repository.memoryrepository.impl

import com.montebruni.sales.infra.repository.memoryrepository.model.OrderMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.port.OrderMemoryRepository
import org.springframework.stereotype.Component

@Component
class OrderMemoryRepositoryImpl : OrderMemoryRepository {

    private val orders: MutableList<OrderMemoryRepositoryModel> = mutableListOf()

    override fun save(order: OrderMemoryRepositoryModel): OrderMemoryRepositoryModel = orders.add(order).let { order }
    override fun getLastOrderNumber(): String? = orders.lastOrNull()?.orderNumber
    override fun findByOrderNumber(orderNumber: String): OrderMemoryRepositoryModel? = orders.find {
        it.orderNumber == orderNumber
    }
}
