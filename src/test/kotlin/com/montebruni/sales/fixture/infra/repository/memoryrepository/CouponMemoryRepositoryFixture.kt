package com.montebruni.sales.fixture.infra.repository.memoryrepository

import com.montebruni.sales.infra.repository.memoryrepository.model.CouponMemoryRepositoryModel

fun createCouponMemoryRepositoryModel() = CouponMemoryRepositoryModel(
    code = "123",
    percentage = 10L
)
