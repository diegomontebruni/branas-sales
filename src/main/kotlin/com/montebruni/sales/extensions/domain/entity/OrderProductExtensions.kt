package com.montebruni.sales.extensions.domain.entity

import com.montebruni.sales.domain.entity.OrderItem
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderItemMemoryRepositoryModel

fun OrderItem.toOrderItemMemoryRepositoryModel() = OrderItemMemoryRepositoryModel(
    id = id,
    orderId = orderId,
    description = description,
    price = price.value.toDouble(),
    quantity = quantity
)
