package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.domain.entity.OrderItem
import com.montebruni.sales.domain.port.OrderItemRepository
import com.montebruni.sales.extensions.domain.entity.toOrderItemMemoryRepositoryModel
import com.montebruni.sales.extensions.repository.memoryrepository.toOrderItem
import com.montebruni.sales.infra.repository.memoryrepository.port.OrderItemMemoryRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderItemMemoryRepositoryAdapter(
    private val orderItemMemoryRepositoryImpl: OrderItemMemoryRepository
) : OrderItemRepository {

    override fun save(orderItem: OrderItem): OrderItem =
        orderItemMemoryRepositoryImpl.save(orderItem.toOrderItemMemoryRepositoryModel()).let { orderItem }

    override fun findById(id: UUID): OrderItem =
        orderItemMemoryRepositoryImpl.findById(id)?.toOrderItem()
            ?: throw IllegalArgumentException("Invalid item")

    override fun findByOrderId(orderId: UUID): List<OrderItem> =
        orderItemMemoryRepositoryImpl.findByOrderId(orderId = orderId).map { it.toOrderItem() }
}
