package com.montebruni.sales.fixture

import com.montebruni.sales.domain.entity.OrderProduct
import com.montebruni.sales.domain.valueobjects.Amount
import java.util.*

fun createOrderProduct() = OrderProduct(
    productId = UUID.randomUUID(),
    price = Amount("100.00"),
    quantity = 10
)
