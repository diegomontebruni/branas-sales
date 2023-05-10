package com.montebruni.sales.infra.repository.postgresql.repository

import com.montebruni.sales.common.DatabaseIT
import com.montebruni.sales.fixture.infra.repository.postgresql.createOrderItemPostgresqlModel
import com.montebruni.sales.fixture.infra.repository.postgresql.createOrderPostgresqlModel
import com.montebruni.sales.fixture.infra.repository.postgresql.createProductPostgresqlModel
import com.montebruni.sales.infra.repository.postgresql.port.OrderItemPostgresqlRepository
import com.montebruni.sales.infra.repository.postgresql.port.OrderPostgresqlRepository
import com.montebruni.sales.infra.repository.postgresql.port.ProductPostgresqlRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class OrderItemPostgresqlRepositoryIT(
    @Autowired private val orderItemRepository: OrderItemPostgresqlRepository,
    @Autowired private val orderRepository: OrderPostgresqlRepository,
    @Autowired private val productRepository: ProductPostgresqlRepository
) : DatabaseIT() {

    @Test
    fun `should find all orders items when given a order id`() {
        val order = createOrderPostgresqlModel().let { orderRepository.save(it) }
        val savedProduct = productRepository.save(createProductPostgresqlModel())

        val orderItemsModel = listOf(
            createOrderItemPostgresqlModel().copy(orderId = order.id, product = savedProduct),
            createOrderItemPostgresqlModel().copy(orderId = order.id, product = savedProduct),
            createOrderItemPostgresqlModel().copy(orderId = order.id, product = savedProduct),
            createOrderItemPostgresqlModel().copy(orderId = order.id, product = savedProduct)
        ).onEach { orderItemRepository.save(it) }

        val orderItems = orderItemRepository.findByOrderId(orderId = order.id)

        assertEquals(orderItemsModel.size, orderItems?.size)
    }

    @Test
    fun `should return empty list when have not items for a order`() {
        assertTrue(orderItemRepository.findByOrderId(UUID.randomUUID()).isNullOrEmpty())
    }
}
