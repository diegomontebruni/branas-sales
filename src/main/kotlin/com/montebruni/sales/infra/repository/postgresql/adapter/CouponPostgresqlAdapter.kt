package com.montebruni.sales.infra.repository.postgresql.adapter

import com.montebruni.sales.application.domain.entity.Coupon
import com.montebruni.sales.application.domain.port.CouponRepository
import com.montebruni.sales.extensions.repository.postgresql.toCoupon
import com.montebruni.sales.infra.repository.postgresql.port.CouponPostgresqlRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CouponPostgresqlAdapter(
    @Autowired private val couponRepository: CouponPostgresqlRepository
) : CouponRepository {

    override fun findByCode(code: String): Coupon = couponRepository.findByCode(code)?.toCoupon()
        ?: throw IllegalArgumentException("coupon not found")
}
