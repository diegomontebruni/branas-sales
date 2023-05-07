package com.montebruni.sales.extensions.domain.entity

import com.montebruni.sales.application.domain.entity.OrderItem
import com.montebruni.sales.infra.repository.postgresql.model.OrderItemPostgresqlModel
import java.util.UUID

fun OrderItem.toOrderItemPostgresqlModel(orderId: UUID) = OrderItemPostgresqlModel(
    id = id,
    product = product.toProductPostgresqlModel(),
    price = price.value.toDouble(),
    quantity = quantity,
    orderId = orderId
)
