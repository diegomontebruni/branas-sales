package com.montebruni.sales.extensions.repository.postgresql

import com.montebruni.sales.application.domain.entity.OrderItem
import com.montebruni.sales.application.domain.valueobjects.Amount
import com.montebruni.sales.infra.repository.postgresql.model.OrderItemPostgresqlModel

fun OrderItemPostgresqlModel.toOrderItem() = OrderItem(
    id = id,
    product = product.toProduct(),
    price = Amount(price),
    quantity = quantity
)
