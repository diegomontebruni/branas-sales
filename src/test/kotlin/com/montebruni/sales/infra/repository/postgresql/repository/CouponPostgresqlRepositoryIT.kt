package com.montebruni.sales.infra.repository.postgresql.repository

import com.montebruni.sales.common.DatabaseIT
import com.montebruni.sales.fixture.infra.repository.postgresql.createCouponPostgresqlModel
import com.montebruni.sales.infra.repository.postgresql.port.CouponPostgresqlRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class CouponPostgresqlRepositoryIT(
    @Autowired private val couponRepository: CouponPostgresqlRepository
) : DatabaseIT() {

    @Test
    fun `should save a coupon successfully`() {
        val saved = couponRepository.save(createCouponPostgresqlModel())
        val coupon = couponRepository.findById(saved.id).get()

        assertNotNull(coupon)
        assertEquals(saved.id, coupon.id)
    }

    @Test
    fun `should get a coupon when given a valid code`() {
        val saved = couponRepository.save(createCouponPostgresqlModel())
        val coupon = couponRepository.findByCode(saved.code)

        assertEquals(saved.id, coupon?.id)
    }

    @Test
    fun `should return null when try to get a coupon with invalid code`() {
        assertNull(couponRepository.findByCode("Desc100"))
    }
}
