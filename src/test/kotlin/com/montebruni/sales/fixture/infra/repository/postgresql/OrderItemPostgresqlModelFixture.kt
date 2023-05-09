package com.montebruni.sales.fixture.infra.repository.postgresql

import com.montebruni.sales.infra.repository.postgresql.model.OrderItemPostgresqlModel
import java.util.*

fun createOrderItemPostgresqlModel() = OrderItemPostgresqlModel(
    product = createProductPostgresqlModel(),
    orderId = UUID.randomUUID(),
    price = 10.0,
    quantity = 1
)
