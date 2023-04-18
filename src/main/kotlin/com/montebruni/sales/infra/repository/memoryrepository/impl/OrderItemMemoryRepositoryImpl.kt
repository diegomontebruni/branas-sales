package com.montebruni.sales.infra.repository.memoryrepository.impl

import com.montebruni.sales.infra.repository.memoryrepository.model.OrderItemMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.port.OrderItemMemoryRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderItemMemoryRepositoryImpl : OrderItemMemoryRepository {

    private val orderItems = mutableListOf<OrderItemMemoryRepositoryModel>()

    override fun save(orderItemModel: OrderItemMemoryRepositoryModel): OrderItemMemoryRepositoryModel =
        orderItems.add(orderItemModel).let { orderItemModel }

    override fun findById(id: UUID): OrderItemMemoryRepositoryModel? = orderItems.find { it.id == id }

    override fun findByOrderId(orderId: UUID): List<OrderItemMemoryRepositoryModel> =
        orderItems.filter { it.orderId == orderId }
}
