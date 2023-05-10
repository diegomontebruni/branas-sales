package com.montebruni.sales.fixture.infra.repository.postgresql

import com.montebruni.sales.infra.repository.postgresql.model.OrderPostgresqlModel
import java.util.*

fun createOrderPostgresqlModel() = OrderPostgresqlModel(
    id = UUID.randomUUID(),
    orderNumber = "202300000001",
    document = "39712790070",
    totalAmount = 100.0,
)
