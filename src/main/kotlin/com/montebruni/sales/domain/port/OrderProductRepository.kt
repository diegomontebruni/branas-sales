package com.montebruni.sales.domain.port

import com.montebruni.sales.domain.entity.OrderProduct
import java.util.UUID

interface OrderProductRepository {
    fun findById(id: UUID): OrderProduct
    fun findByOrderId(orderId: UUID): List<OrderProduct>
}
