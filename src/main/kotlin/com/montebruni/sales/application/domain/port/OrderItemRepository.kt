package com.montebruni.sales.application.domain.port

import com.montebruni.sales.application.domain.entity.OrderItem
import java.util.UUID

interface OrderItemRepository {
    fun save(orderItem: OrderItem): OrderItem
    fun findById(id: UUID): OrderItem
    fun findByOrderId(orderId: UUID): List<OrderItem>
}
