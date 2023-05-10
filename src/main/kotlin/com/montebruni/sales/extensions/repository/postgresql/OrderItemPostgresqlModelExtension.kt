package com.montebruni.sales.extensions.repository.postgresql

import com.montebruni.sales.application.domain.entity.Item
import com.montebruni.sales.application.domain.valueobjects.Amount
import com.montebruni.sales.infra.repository.postgresql.model.ItemPostgresqlModel

fun ItemPostgresqlModel.toOrderItem() = Item(
    id = id,
    product = product.toProduct(),
    price = Amount(price),
    quantity = quantity
)
