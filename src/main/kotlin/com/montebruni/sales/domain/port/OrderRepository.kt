package com.montebruni.sales.domain.port

import com.montebruni.sales.domain.entity.Order

interface OrderRepository {
    fun save(order: Order): Order
}
