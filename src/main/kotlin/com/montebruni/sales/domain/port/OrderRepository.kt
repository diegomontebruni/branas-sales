package com.montebruni.sales.domain.port

import com.montebruni.sales.domain.entity.Order
import java.util.UUID

interface OrderRepository {
    fun findById(id: UUID): Order
}
