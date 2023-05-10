package com.montebruni.sales.fixture.domain

import com.montebruni.sales.application.domain.entity.Coupon
import java.time.Instant
import java.util.*

fun createCoupon() = Coupon(
    id = UUID.randomUUID(),
    code = "123",
    percentage = 10,
    expirationAt = Instant.now().plusSeconds(1000)
)

fun createExpiredCoupon() = Coupon(
    id = UUID.randomUUID(),
    code = "Exp1",
    percentage = 10,
    expirationAt = Instant.now().minusSeconds(1000)
)
