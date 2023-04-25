package com.montebruni.sales.fixture.domain

import com.montebruni.sales.application.domain.entity.Order
import com.montebruni.sales.application.domain.valueobjects.Document

fun createOrder() = Order(
    id = Order.generateId(),
    document = Document("40369365062"),
    items = mutableListOf(createOrderItem(), createOrderItem(), createOrderItem())
)

fun createOrderWithCoupon() = Order(
    id = Order.generateId(),
    document = Document("40369365062"),
    items = mutableListOf(createOrderItem(), createOrderItem(), createOrderItem()),
    coupon = createCoupon()
)
