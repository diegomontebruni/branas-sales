package com.montebruni.sales.infra.repository.memoryrepository.datasource

import com.montebruni.sales.infra.repository.memoryrepository.model.CouponMemoryRepositoryModel
import java.time.Instant

fun createCouponDatasource() = listOf(
    CouponMemoryRepositoryModel(
        code = "DESC10", percentage = 10, expirationAt = Instant.now().plusSeconds(1000)),
    CouponMemoryRepositoryModel(
        code = "DESC20", percentage = 20, expirationAt = Instant.now().plusSeconds(1000)),
    CouponMemoryRepositoryModel(
        code = "DESC30", percentage = 30, expirationAt = Instant.now().plusSeconds(1000)),
    CouponMemoryRepositoryModel(
        code = "DESC40", percentage = 40, expirationAt = Instant.now().plusSeconds(1000)),
    CouponMemoryRepositoryModel(
        code = "DESC50", percentage = 50, expirationAt = Instant.now().plusSeconds(1000)),
    CouponMemoryRepositoryModel(
        code = "DESC5", percentage = 5, expirationAt = Instant.now().minusSeconds(1000)),
    CouponMemoryRepositoryModel(
        code = "DESC3", percentage = 3, expirationAt = Instant.now().minusSeconds(1000)),
)
