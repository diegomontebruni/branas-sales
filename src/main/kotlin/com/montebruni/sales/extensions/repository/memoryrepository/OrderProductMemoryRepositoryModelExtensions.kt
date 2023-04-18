package com.montebruni.sales.extensions.repository.memoryrepository

import com.montebruni.sales.domain.entity.OrderItem
import com.montebruni.sales.domain.valueobjects.Amount
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderItemMemoryRepositoryModel

fun OrderItemMemoryRepositoryModel.toOrderItem() = OrderItem(
    id = id,
    orderId = orderId,
    description = description,
    price = Amount(price),
    quantity = quantity
)
