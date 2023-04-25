package com.montebruni.sales.extensions.domain.entity

import com.montebruni.sales.application.domain.entity.OrderItem
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderItemMemoryRepositoryModel

fun OrderItem.toOrderItemMemoryRepositoryModel() = OrderItemMemoryRepositoryModel(
    id = id,
    orderId = orderId,
    productId = product.id,
    price = price.value.toDouble(),
    quantity = quantity
)
