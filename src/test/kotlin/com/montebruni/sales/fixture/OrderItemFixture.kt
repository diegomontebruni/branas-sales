package com.montebruni.sales.fixture

import com.montebruni.sales.domain.entity.OrderItem
import com.montebruni.sales.domain.valueobjects.Amount
import java.util.*

fun createOrderItem() = OrderItem(
    description = "Description Test",
    orderId = UUID.randomUUID(),
    price = Amount("100.00"),
    quantity = 10
)
