package com.montebruni.sales.infra.repository.memoryrepository.port

import com.montebruni.sales.infra.repository.memoryrepository.model.CouponMemoryRepositoryModel

interface CouponMemoryRepository {
    fun findByCode(code: String) : CouponMemoryRepositoryModel?
}
