package com.montebruni.sales.infra.repository.postgresql

import com.montebruni.sales.infra.repository.postgresql.model.CouponPostgresqlModel
import com.montebruni.sales.infra.repository.postgresql.port.CouponPostgresqlRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.Instant

class CouponPostgresqlRepositoryIT(
    @Autowired private val couponPostgresqlRepository: CouponPostgresqlRepository
) {

    @Test
    fun `should save a coupon successfully`() {
        val coupon = CouponPostgresqlModel(
            code = "123",
            percentage = 1,
            expirationAt = Instant.now().plusSeconds(10)
        )

        val saved = couponPostgresqlRepository.save(coupon)

        assertEquals(coupon.id, saved.id)
    }
}
