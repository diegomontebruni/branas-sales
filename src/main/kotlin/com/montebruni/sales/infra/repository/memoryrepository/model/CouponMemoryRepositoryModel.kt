package com.montebruni.sales.infra.repository.memoryrepository.model

import java.time.Instant

data class CouponMemoryRepositoryModel(
    val code: String,
    val percentage: Int,
    val expirationAt: Instant
)
