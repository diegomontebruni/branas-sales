package com.montebruni.sales.fixture

import com.montebruni.sales.domain.entity.OrderProduct
import com.montebruni.sales.domain.valueobjects.Amount

fun createOrderProduct() = OrderProduct(
    description = "Description Test",
    price = Amount("100.00"),
    quantity = 10
)
