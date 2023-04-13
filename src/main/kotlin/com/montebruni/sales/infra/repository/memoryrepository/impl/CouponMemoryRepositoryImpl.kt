package com.montebruni.sales.infra.repository.memoryrepository.impl

import com.montebruni.sales.infra.repository.memoryrepository.datasource.createCouponDatasource
import com.montebruni.sales.infra.repository.memoryrepository.model.CouponMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.port.CouponMemoryRepository
import org.springframework.stereotype.Component

@Component
class CouponMemoryRepositoryImpl : CouponMemoryRepository {

    private val coupons = createCouponDatasource()

    override fun findByCode(code: String): CouponMemoryRepositoryModel? = coupons.find { it.code == code }
}
