package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.domain.entity.Coupon
import com.montebruni.sales.domain.port.CouponRepository
import com.montebruni.sales.extensions.repository.memoryrepository.toCoupon
import com.montebruni.sales.infra.repository.memoryrepository.port.CouponMemoryRepository
import org.springframework.stereotype.Component

@Component
class CouponMemoryRepositoryAdapter(
    private val couponMemoryRepository: CouponMemoryRepository
) : CouponRepository {

    override fun findByCode(code: String): Coupon =
        couponMemoryRepository.findByCode(code)?.toCoupon() ?: throw IllegalArgumentException("Coupon code not found")
}
