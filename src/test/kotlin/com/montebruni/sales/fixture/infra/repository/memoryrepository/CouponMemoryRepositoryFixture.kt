package com.montebruni.sales.fixture.infra.repository.memoryrepository

import com.montebruni.sales.infra.repository.memoryrepository.model.CouponMemoryRepositoryModel
import java.time.Instant

fun createCouponMemoryRepositoryModel() = CouponMemoryRepositoryModel(
    code = "123",
    percentage = 10,
    expirationAt = Instant.now().plusSeconds(1000)
)
