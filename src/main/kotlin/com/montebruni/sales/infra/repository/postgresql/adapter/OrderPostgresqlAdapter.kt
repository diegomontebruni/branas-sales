package com.montebruni.sales.infra.repository.postgresql.adapter

import com.montebruni.sales.application.domain.entity.Item
import com.montebruni.sales.application.domain.entity.Order
import com.montebruni.sales.application.domain.port.OrderRepository
import com.montebruni.sales.application.domain.port.ProductRepository
import com.montebruni.sales.extensions.domain.entity.toItemPostgresqlModel
import com.montebruni.sales.extensions.domain.entity.toOrderPostgresqlModel
import com.montebruni.sales.extensions.repository.postgresql.toOrder
import com.montebruni.sales.infra.repository.postgresql.port.ItemPostgresqlRepository
import com.montebruni.sales.infra.repository.postgresql.port.OrderPostgresqlRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderPostgresqlAdapter(
    @Autowired private val orderRepository: OrderPostgresqlRepository,
    @Autowired private val itemRepository: ItemPostgresqlRepository,
    @Autowired private val productRepository: ProductRepository
) : OrderRepository {

    @Transactional
    override fun save(order: Order) {
        val savedOrder = orderRepository.save(order.toOrderPostgresqlModel())

        order.items.map { item -> itemRepository.save(item.toItemPostgresqlModel(savedOrder.id)) }
    }

    override fun getLastOrderNumber(): String? = orderRepository.findTopByOrderByCreatedAtDesc()?.orderNumber

    override fun findByOrderNumber(orderNumber: String): Order = orderRepository.findByOrderNumber(orderNumber)?.let {
        it.toOrder(getOrderItemsByOrderId(it.id))
    } ?: throw IllegalArgumentException("Order not found for order number $orderNumber")

    override fun getOrders(): List<Order> = orderRepository.findAll().map {
            orderModel ->
        orderModel.toOrder(getOrderItemsByOrderId(orderModel.id))
    }

    private fun getOrderItemsByOrderId(orderId: UUID): List<Item> =
        itemRepository.findByOrderId(orderId)?.map {
            Item(
                id = it.id,
                quantity = it.quantity,
                product = productRepository.findById(it.id)
            )
        }
            ?: throw IllegalArgumentException("Items not found for order id $orderId")
}
