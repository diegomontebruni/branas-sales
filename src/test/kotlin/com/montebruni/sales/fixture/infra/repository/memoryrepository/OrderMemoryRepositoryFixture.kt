package com.montebruni.sales.fixture.infra.repository.memoryrepository

import com.montebruni.sales.infra.repository.memoryrepository.model.OrderMemoryRepositoryModel
import java.util.*

fun createOrderMemoryRepositoryModel() = OrderMemoryRepositoryModel(
    id = UUID.randomUUID(),
    document = "38592226007",
    totalAmount = 10.0
)
