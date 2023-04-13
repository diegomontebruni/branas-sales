package com.montebruni.sales.infra.repository.memoryrepository.datasource

import com.montebruni.sales.infra.repository.memoryrepository.output.CouponMemoryRepositoryOutput

fun createCouponDatasource() = listOf(
    CouponMemoryRepositoryOutput(code = "1", percentage = 10L),
    CouponMemoryRepositoryOutput(code = "2", percentage = 20L),
    CouponMemoryRepositoryOutput(code = "3", percentage = 30L),
    CouponMemoryRepositoryOutput(code = "4", percentage = 40L),
    CouponMemoryRepositoryOutput(code = "5", percentage = 50L),
)
