package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.infra.repository.memoryrepository.impl.CouponMemoryRepositoryImpl
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CouponMemoryRepositoryTests : UnitTests() {

    @InjectMockKs
    private lateinit var couponMemoryRepositoryImpl: CouponMemoryRepositoryImpl

    @Test
    fun `should return coupon successfully when has a valid code`() {
        val code = "DESC10"

        val coupon = couponMemoryRepositoryImpl.findByCode(code)

        assertNotNull(coupon)
        assertEquals(code, coupon?.code)
    }

    @Test
    fun `should return null when has an invalid code`() {
        val code = "1000"
        val coupon = couponMemoryRepositoryImpl.findByCode(code)

        assertNull(coupon)
    }
}
