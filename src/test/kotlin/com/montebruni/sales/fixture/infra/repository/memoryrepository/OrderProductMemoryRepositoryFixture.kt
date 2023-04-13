package com.montebruni.sales.fixture.infra.repository.memoryrepository

import com.montebruni.sales.infra.repository.memoryrepository.model.OrderProductMemoryRepositoryModel
import java.util.*

fun createOrderProductMemoryRepositoryModel() = OrderProductMemoryRepositoryModel(
    id = UUID.randomUUID(),
    orderId = UUID.randomUUID(),
    description = "Order product model",
    price = 10.00,
    quantity = 10
)
