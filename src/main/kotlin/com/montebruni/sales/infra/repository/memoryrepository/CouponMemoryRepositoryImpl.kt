package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.domain.entity.Coupon
import com.montebruni.sales.domain.port.CouponRepository
import com.montebruni.sales.extensions.repository.memoryrepository.toCoupon
import com.montebruni.sales.infra.repository.memoryrepository.datasource.createCouponDatasource

class CouponMemoryRepositoryImpl : CouponRepository {

    private val coupons = createCouponDatasource()

    override fun findByCode(code: String): Coupon =
        coupons.find { it.code == code }?.toCoupon() ?: throw IllegalArgumentException("Coupon code not found")
}
