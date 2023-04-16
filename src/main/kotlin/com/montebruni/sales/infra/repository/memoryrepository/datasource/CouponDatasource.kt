package com.montebruni.sales.infra.repository.memoryrepository.datasource

import com.montebruni.sales.infra.repository.memoryrepository.model.CouponMemoryRepositoryModel

fun createCouponDatasource() = listOf(
    CouponMemoryRepositoryModel(code = "1", percentage = 10L),
    CouponMemoryRepositoryModel(code = "2", percentage = 20L),
    CouponMemoryRepositoryModel(code = "3", percentage = 30L),
    CouponMemoryRepositoryModel(code = "4", percentage = 40L),
    CouponMemoryRepositoryModel(code = "5", percentage = 50L),
)
