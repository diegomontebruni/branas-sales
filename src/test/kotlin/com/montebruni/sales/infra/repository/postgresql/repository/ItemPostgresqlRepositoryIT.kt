package com.montebruni.sales.infra.repository.postgresql.repository

import com.montebruni.sales.common.DatabaseIT
import com.montebruni.sales.fixture.infra.repository.postgresql.createItemPostgresqlModel
import com.montebruni.sales.fixture.infra.repository.postgresql.createOrderPostgresqlModel
import com.montebruni.sales.infra.repository.postgresql.port.ItemPostgresqlRepository
import com.montebruni.sales.infra.repository.postgresql.port.OrderPostgresqlRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class ItemPostgresqlRepositoryIT(
    @Autowired private val orderItemRepository: ItemPostgresqlRepository,
    @Autowired private val orderRepository: OrderPostgresqlRepository
) : DatabaseIT() {

    @Test
    fun `should find all orders items when given a order id`() {
        val order = createOrderPostgresqlModel().let { orderRepository.save(it) }

        val orderItemsModel = listOf(
            createItemPostgresqlModel().copy(orderId = order.id),
            createItemPostgresqlModel().copy(orderId = order.id),
            createItemPostgresqlModel().copy(orderId = order.id),
            createItemPostgresqlModel().copy(orderId = order.id)
        ).onEach { orderItemRepository.save(it) }

        val orderItems = orderItemRepository.findByOrderId(orderId = order.id)

        assertEquals(orderItemsModel.size, orderItems?.size)
    }

    @Test
    fun `should return empty list when have not items for a order`() {
        assertTrue(orderItemRepository.findByOrderId(UUID.randomUUID()).isNullOrEmpty())
    }
}
