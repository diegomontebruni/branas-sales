package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.domain.entity.OrderItem
import com.montebruni.sales.domain.port.OrderItemRepository
import com.montebruni.sales.extensions.domain.entity.toOrderItemMemoryRepositoryModel
import com.montebruni.sales.extensions.repository.memoryrepository.toOrderItem
import com.montebruni.sales.extensions.repository.memoryrepository.toProduct
import com.montebruni.sales.infra.repository.memoryrepository.model.ProductMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.port.OrderItemMemoryRepository
import com.montebruni.sales.infra.repository.memoryrepository.port.ProductMemoryRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderItemMemoryRepositoryAdapter(
    private val orderItemMemoryRepository: OrderItemMemoryRepository,
    private val productMemoryRepository: ProductMemoryRepository
) : OrderItemRepository {

    override fun save(orderItem: OrderItem): OrderItem =
        orderItemMemoryRepository.save(orderItem.toOrderItemMemoryRepositoryModel()).let { orderItem }

    override fun findById(id: UUID): OrderItem =
        orderItemMemoryRepository.findById(id)?.let {
            it.toOrderItem(product = findProductById(it.productId).toProduct())
        } ?: throw IllegalArgumentException("Invalid item")

    override fun findByOrderId(orderId: UUID): List<OrderItem> =
        orderItemMemoryRepository.findByOrderId(orderId = orderId).map {
            it.toOrderItem(product = findProductById(it.productId).toProduct())
        }

    private fun findProductById(id: UUID): ProductMemoryRepositoryModel =
        productMemoryRepository.findById(id) ?: throw IllegalArgumentException("Invalid item")
}
