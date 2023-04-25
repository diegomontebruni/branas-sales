package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.application.domain.entity.Order
import com.montebruni.sales.application.domain.port.OrderRepository
import com.montebruni.sales.extensions.domain.entity.toOrderMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.port.OrderMemoryRepository
import org.springframework.stereotype.Component

@Component
class OrderMemoryRepositoryAdapter(
    private val orderMemoryRepository: OrderMemoryRepository
) : OrderRepository {

    override fun save(order: Order): Order =
        orderMemoryRepository.save(order.toOrderMemoryRepositoryModel()).let { order }
}
