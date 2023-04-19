package com.montebruni.sales.fixture

import com.montebruni.sales.domain.entity.Coupon
import java.time.Instant

fun createCoupon() = Coupon(
    code = "123", percentage = 10, expirationAt = Instant.now().plusSeconds(1000)
)

fun createExpiredCoupon() = Coupon(
    code = "Exp1", percentage = 10, expirationAt = Instant.now().minusSeconds(1000)
)
