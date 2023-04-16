package com.montebruni.sales.fixture

import com.montebruni.sales.domain.entity.Order
import com.montebruni.sales.domain.valueobjects.Document

fun createOrder() = Order(
    document = Document("40369365062"),
    products = mutableListOf(createOrderProduct(), createOrderProduct(), createOrderProduct())
)

fun createOrderWithCoupon() = Order(
    document = Document("40369365062"),
    products = mutableListOf(createOrderProduct(), createOrderProduct(), createOrderProduct()),
    coupon = createCoupon()
)
