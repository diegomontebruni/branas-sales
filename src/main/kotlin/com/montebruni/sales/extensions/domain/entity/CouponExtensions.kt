package com.montebruni.sales.extensions.domain.entity

import com.montebruni.sales.application.domain.entity.Coupon
import com.montebruni.sales.infra.repository.postgresql.model.CouponPostgresqlModel

fun Coupon.toCouponPostgresqlModel() = CouponPostgresqlModel(
    id = id,
    code = code,
    percentage = percentage,
    expirationAt = expirationAt
)
