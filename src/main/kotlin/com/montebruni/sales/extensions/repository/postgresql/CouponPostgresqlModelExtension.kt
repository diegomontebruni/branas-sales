package com.montebruni.sales.extensions.repository.postgresql

import com.montebruni.sales.application.domain.entity.Coupon
import com.montebruni.sales.infra.repository.postgresql.model.CouponPostgresqlModel

fun CouponPostgresqlModel.toCoupon() = Coupon(
    id = id,
    code = code,
    percentage = percentage,
    expirationAt = expirationAt
)
