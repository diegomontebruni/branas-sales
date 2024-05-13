package com.montebruni.sales.extensions.repository.postgresql

import com.montebruni.sales.application.domain.entity.Item
import com.montebruni.sales.application.domain.entity.Order
import com.montebruni.sales.application.domain.valueobjects.Document
import com.montebruni.sales.application.domain.valueobjects.OrderNumber
import com.montebruni.sales.infra.repository.postgresql.model.OrderPostgresqlModel

fun OrderPostgresqlModel.toOrder(items: List<Item>) = Order(
    id = id,
    orderNumber = OrderNumber(orderNumber),
    document = Document(document),
    items = items
)
