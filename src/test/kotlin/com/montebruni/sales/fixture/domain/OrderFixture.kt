package com.montebruni.sales.fixture.domain

import com.montebruni.sales.application.domain.entity.Order
import com.montebruni.sales.application.domain.valueobjects.Document
import com.montebruni.sales.application.domain.valueobjects.OrderNumber

fun createOrder() = Order(
    id = Order.generateId(),
    document = Document("40369365062"),
    items = mutableListOf(createOrderItem(), createOrderItem(), createOrderItem()),
    orderNumber = OrderNumber()
)

fun createOrderWithCoupon() = Order(
    id = Order.generateId(),
    document = Document("40369365062"),
    items = mutableListOf(createOrderItem(), createOrderItem(), createOrderItem()),
    coupon = createCoupon(),
    orderNumber = OrderNumber()
)
