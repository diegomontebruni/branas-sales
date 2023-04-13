package com.montebruni.sales.fixture

import com.montebruni.sales.domain.entity.Order
import java.util.*

fun createOrder() = Order(
    customerId = UUID.randomUUID(),
    products = mutableListOf(createProduct(), createProduct(), createProduct())
)

fun createOrderWithCoupon() = Order(
    customerId = UUID.randomUUID(),
    products = mutableListOf(createProduct(), createProduct(), createProduct()),
    coupon = createCoupon()
)
