package com.montebruni.sales.extensions.domain.entity

import com.montebruni.sales.application.domain.entity.OrderItem
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderItemMemoryRepositoryModel
import com.montebruni.sales.infra.repository.postgresql.model.OrderItemPostgresqlModel
import java.util.*

fun OrderItem.toOrderItemMemoryRepositoryModel() = OrderItemMemoryRepositoryModel(
    id = id,
    orderId = UUID.randomUUID(),
    productId = product.id,
    price = price.value.toDouble(),
    quantity = quantity
)

fun OrderItem.toOrderItemPostgresqlModel() = OrderItemPostgresqlModel(
    id = id,
    product = product.toProductPostgresqlModel(),
    price = price.value.toDouble(),
    quantity = quantity
)
