package com.montebruni.sales.fixture

import com.montebruni.sales.domain.entity.Order
import java.util.*

fun createOrder() = Order(
    customerId = UUID.randomUUID(),
    products = mutableListOf(createOrderProduct(), createOrderProduct(), createOrderProduct())
)

fun createOrderWithCoupon() = Order(
    customerId = UUID.randomUUID(),
    products = mutableListOf(createOrderProduct(), createOrderProduct(), createOrderProduct()),
    coupon = createCoupon()
)
