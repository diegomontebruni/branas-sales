package com.montebruni.sales.fixture.infra.repository.postgresql

import com.montebruni.sales.infra.repository.postgresql.model.ItemPostgresqlModel
import java.util.*

fun createItemPostgresqlModel() = ItemPostgresqlModel(
    productId = UUID.randomUUID(),
    orderId = UUID.randomUUID(),
    quantity = 1
)
