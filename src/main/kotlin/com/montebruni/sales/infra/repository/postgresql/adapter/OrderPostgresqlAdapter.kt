package com.montebruni.sales.infra.repository.postgresql.adapter

import com.montebruni.sales.application.domain.entity.Order
import com.montebruni.sales.application.domain.port.OrderRepository
import com.montebruni.sales.extensions.domain.entity.toOrderPostgresqlModel
import com.montebruni.sales.extensions.repository.postgresql.toOrder
import com.montebruni.sales.infra.repository.postgresql.model.OrderItemPostgresqlModel
import com.montebruni.sales.infra.repository.postgresql.port.OrderItemPostgresqlRepository
import com.montebruni.sales.infra.repository.postgresql.port.OrderPostgresqlRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderPostgresqlAdapter(
    @Autowired private val orderRepository: OrderPostgresqlRepository,
    @Autowired private val orderItemRepository: OrderItemPostgresqlRepository
) : OrderRepository {

    override fun save(order: Order): Unit = orderRepository.save(order.toOrderPostgresqlModel()).let {  }

    override fun getLastOrderNumber(): String? = orderRepository.findTopByOrderByCreatedAtDesc()?.orderNumber

    override fun findByOrderNumber(orderNumber: String): Order = orderRepository.findByOrderNumber(orderNumber)?.let {
        it.toOrder(getOrderItemsByOrderId(it.id))
    } ?: throw IllegalArgumentException("Order not found for order number $orderNumber")

    override fun getOrders(): List<Order> = orderRepository.findAll().map {
        orderModel -> orderModel.toOrder(getOrderItemsByOrderId(orderModel.id))
    }

    private fun getOrderItemsByOrderId(orderId: UUID): List<OrderItemPostgresqlModel> =
        orderItemRepository.findByOrderId(orderId) ?:
            throw IllegalArgumentException("Items not found for order id $orderId")
}
