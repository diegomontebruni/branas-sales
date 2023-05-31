package com.montebruni.sales.extensions.repository.postgresql

import com.montebruni.sales.application.domain.entity.Item
import com.montebruni.sales.application.domain.entity.Product
import com.montebruni.sales.infra.repository.postgresql.model.ItemPostgresqlModel

fun ItemPostgresqlModel.toOrderItem(product: Product) = Item(
    id = id,
    product = product,
    quantity = quantity
)
