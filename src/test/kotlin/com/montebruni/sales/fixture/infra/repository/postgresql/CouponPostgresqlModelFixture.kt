package com.montebruni.sales.fixture.infra.repository.postgresql

import com.montebruni.sales.infra.repository.postgresql.model.CouponPostgresqlModel
import java.time.Instant

fun createCouponPostgresqlModel() = CouponPostgresqlModel(
    code = "DESC10",
    percentage = 10,
    expirationAt = Instant.now().plusSeconds(50)
)

fun createExpiredCouponPostgresqlModel() = CouponPostgresqlModel(
    code = "DESC10",
    percentage = 10,
    expirationAt = Instant.now().minusSeconds(50)
)
