package com.montebruni.sales.infra.repository.memoryrepository.model

import java.util.*

data class OrderItemMemoryRepositoryModel(
    val id: UUID,
    val orderId: UUID,
    val productId: UUID,
    val price: Double,
    val quantity: Int
)
