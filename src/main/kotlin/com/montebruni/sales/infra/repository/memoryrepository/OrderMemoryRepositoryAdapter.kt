package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.application.domain.entity.Order
import com.montebruni.sales.application.domain.port.OrderRepository
import com.montebruni.sales.extensions.domain.entity.toOrderItemMemoryRepositoryModel
import com.montebruni.sales.extensions.domain.entity.toOrderMemoryRepositoryModel
import com.montebruni.sales.extensions.repository.memoryrepository.toCoupon
import com.montebruni.sales.extensions.repository.memoryrepository.toOrder
import com.montebruni.sales.extensions.repository.memoryrepository.toOrderItem
import com.montebruni.sales.infra.repository.memoryrepository.port.CouponMemoryRepository
import com.montebruni.sales.infra.repository.memoryrepository.port.OrderItemMemoryRepository
import com.montebruni.sales.infra.repository.memoryrepository.port.OrderMemoryRepository
import com.montebruni.sales.infra.repository.memoryrepository.port.ProductMemoryRepository
import org.springframework.stereotype.Component

@Component
class OrderMemoryRepositoryAdapter(
    private val orderMemoryRepository: OrderMemoryRepository,
    private val orderItemMemoryRepository: OrderItemMemoryRepository,
    private val productMemoryRepository: ProductMemoryRepository,
    private val couponMemoryRepository: CouponMemoryRepository
) : OrderRepository {

    override fun save(order: Order): Order =
        orderMemoryRepository.save(order.toOrderMemoryRepositoryModel()).let { order }.also {
            order.items.forEach { orderItemMemoryRepository.save(it.toOrderItemMemoryRepositoryModel()) }
        }

    override fun getLastOrderNumber(): String? = orderMemoryRepository.getLastOrderNumber()

    override fun findByOrderNumber(orderNumber: String): Order =
        orderMemoryRepository.findByOrderNumber(orderNumber)?.let { orderModel ->
            orderModel.toOrder(
                items = orderItemMemoryRepository.findByOrderId(orderModel.id).map { orderItemModel ->
                    orderItemModel.toOrderItem(productMemoryRepository.findById(orderItemModel.productId)!!)
                },
                coupon = orderModel.couponCode?.let { couponMemoryRepository.findByCode(it)?.toCoupon() }
            )
        } ?: throw IllegalArgumentException("Order not found for order number")

    override fun getOrders(): List<Order> = orderMemoryRepository.getAllOrders().map {
        findByOrderNumber(it.orderNumber)
    }
}
