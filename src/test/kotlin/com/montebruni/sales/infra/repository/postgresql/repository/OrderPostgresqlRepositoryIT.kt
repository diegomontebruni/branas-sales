package com.montebruni.sales.infra.repository.postgresql.repository

import com.montebruni.sales.common.DatabaseIT
import com.montebruni.sales.fixture.infra.repository.postgresql.createCouponPostgresqlModel
import com.montebruni.sales.fixture.infra.repository.postgresql.createOrderPostgresqlModel
import com.montebruni.sales.infra.repository.postgresql.port.CouponPostgresqlRepository
import com.montebruni.sales.infra.repository.postgresql.port.OrderPostgresqlRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class OrderPostgresqlRepositoryIT(
    @Autowired private val orderRepository: OrderPostgresqlRepository,
    @Autowired private val couponRepository: CouponPostgresqlRepository
) : DatabaseIT() {

    @Nested
    @DisplayName("findTopByOrderByCreatedAtDesc")
    inner class FindTopByOrderByCreatedAtDescTestCases {

        @Test
        fun `should find last inserted order`() {
            orderRepository.save(createOrderPostgresqlModel())
            orderRepository.save(createOrderPostgresqlModel())
            orderRepository.save(createOrderPostgresqlModel())
            val last = orderRepository.save(createOrderPostgresqlModel())
            val lastOrder = orderRepository.findTopByOrderByCreatedAtDesc()

            assertEquals(last.id, lastOrder?.id)
        }

        @Test
        fun `should return null when database is empty`() {
            assertNull(orderRepository.findTopByOrderByCreatedAtDesc())
        }
    }

    @Nested
    @DisplayName("findByOrderNumber")
    inner class FindByOrderNumberTestCases {

        @Test
        fun `should find orders whe given valid order number`() {
            val savedOrder = orderRepository.save(createOrderPostgresqlModel())
            val order = orderRepository.findByOrderNumber(savedOrder.orderNumber)

            assertEquals(savedOrder.id, order?.id)
        }

        @Test
        fun `should return null when given a invalid order number`() {
            assertNull(orderRepository.findByOrderNumber("123"))
        }
    }

    @Nested
    @DisplayName("find order with coupon")
    inner class FindOrderWithCouponTestCases {

        @Test
        fun `should find order with coupon`() {
            val savedCoupon = couponRepository.save(createCouponPostgresqlModel())
            val savedOrder = orderRepository.save(createOrderPostgresqlModel().copy(coupon = savedCoupon))
            val order = orderRepository.findByOrderNumber(savedOrder.orderNumber)

            assertEquals(savedCoupon.id, order?.coupon?.id)
        }
    }
}
