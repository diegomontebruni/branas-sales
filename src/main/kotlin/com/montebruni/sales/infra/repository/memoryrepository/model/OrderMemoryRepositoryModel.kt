package com.montebruni.sales.infra.repository.memoryrepository.model

import java.util.*

data class OrderMemoryRepositoryModel(
    val id: UUID,
    val orderNumber: String,
    val document: String,
    val totalAmount: Double,
    val couponCode: String? = null,
)
