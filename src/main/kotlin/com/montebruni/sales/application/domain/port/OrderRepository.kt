package com.montebruni.sales.application.domain.port

import com.montebruni.sales.application.domain.entity.Order

interface OrderRepository {
    fun save(order: Order): Order
    fun getLastOrderNumber(): String?
    fun findByOrderNumber(orderNumber: String): Order
}
