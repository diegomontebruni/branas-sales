package com.montebruni.sales.infra.repositor.memoryrepository

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createCouponMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.CouponMemoryRepositoryAdapter
import com.montebruni.sales.infra.repository.memoryrepository.port.CouponMemoryRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CouponMemoryRepositoryAdapterTests(
    @MockK private val  couponMemoryRepository : CouponMemoryRepository
) : UnitTests() {

    @InjectMockKs
    private lateinit var couponMemoryRepositoryAdapter: CouponMemoryRepositoryAdapter

    @Test
    fun `should find coupon successfully when code is valid`() {
        val couponModel = createCouponMemoryRepositoryModel()
        val code = "123"
        val codeSlot = slot<String>()

        every { couponMemoryRepository.findByCode(capture(codeSlot)) } returns couponModel

        val coupon = couponMemoryRepositoryAdapter.findByCode(code)

        assertEquals(code, codeSlot.captured)
        assertEquals(code, coupon.code)
        assertEquals(couponModel.percentage, coupon.percentage)

        verify {
            couponMemoryRepository.findByCode(codeSlot.captured)
        }
    }

    @Test
    fun `should throw exception when coupon has a invalid code`() {
        val code = "123"
        val codeSlot = slot<String>()

        every { couponMemoryRepository.findByCode(capture(codeSlot)) } returns null

        assertThrows<IllegalArgumentException> { couponMemoryRepositoryAdapter.findByCode(code) }

        assertEquals(code, codeSlot.captured)

        verify {
            couponMemoryRepository.findByCode(codeSlot.captured)
        }
    }
}
