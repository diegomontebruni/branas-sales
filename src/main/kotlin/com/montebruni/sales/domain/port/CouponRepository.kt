package com.montebruni.sales.domain.port

import com.montebruni.sales.domain.entity.Coupon

interface CouponRepository {
    fun findByCode(code: String): Coupon
}
