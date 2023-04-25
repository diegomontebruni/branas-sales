package com.montebruni.sales.fixture.domain

import com.montebruni.sales.application.domain.entity.OrderItem
import com.montebruni.sales.application.domain.valueobjects.Amount
import java.util.*

fun createOrderItem() = OrderItem(
    product = createProduct(),
    orderId = UUID.randomUUID(),
    price = Amount("100.00"),
    quantity = 10
)
