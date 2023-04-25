package com.montebruni.sales.application.domain.port

import com.montebruni.sales.application.domain.entity.Coupon

interface CouponRepository {
    fun findByCode(code: String): Coupon
}
