package com.montebruni.sales.fixture.infra.repository.memoryrepository

import com.montebruni.sales.infra.repository.memoryrepository.model.OrderItemMemoryRepositoryModel
import java.util.*

fun createOrderItemMemoryRepositoryModel() = OrderItemMemoryRepositoryModel(
    id = UUID.randomUUID(),
    orderId = UUID.randomUUID(),
    description = "Order item model",
    price = 10.00,
    quantity = 10
)
