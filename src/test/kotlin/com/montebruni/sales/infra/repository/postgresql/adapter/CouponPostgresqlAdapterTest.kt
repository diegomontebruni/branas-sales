package com.montebruni.sales.infra.repository.postgresql.adapter

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.infra.repository.postgresql.createCouponPostgresqlModel
import com.montebruni.sales.infra.repository.postgresql.port.CouponPostgresqlRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import java.lang.IllegalArgumentException

class CouponPostgresqlAdapterTest(
    @MockK private val couponRepository: CouponPostgresqlRepository
) : UnitTests() {

    @InjectMockKs
    private lateinit var couponPostgresqlAdapter: CouponPostgresqlAdapter

    @Test
    fun `should return coupon entity when find by coupon code`() {
        val expectedCoupon = createCouponPostgresqlModel()
        val couponCode = expectedCoupon.code

        every { couponRepository.findByCode(couponCode) } returns expectedCoupon

        val couponEntity = couponPostgresqlAdapter.findByCode(couponCode)

        assertEquals(expectedCoupon.id, couponEntity.id)
        assertEquals(expectedCoupon.code, couponEntity.code)

        verify { couponRepository.findByCode(couponCode) }
    }

    @Test
    fun `should throw exception when try to find coupon by coupon code`() {
        val couponCode = "123"

        every { couponRepository.findByCode(couponCode) } returns null

        assertThrows<IllegalArgumentException> { couponPostgresqlAdapter.findByCode(couponCode) }

        verify { couponRepository.findByCode(couponCode) }
    }
}
