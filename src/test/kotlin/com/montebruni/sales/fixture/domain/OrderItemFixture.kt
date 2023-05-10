package com.montebruni.sales.fixture.domain

import com.montebruni.sales.application.domain.entity.Item
import com.montebruni.sales.application.domain.valueobjects.Amount

fun createOrderItem() = Item(
    product = createProduct(),
    price = Amount("100.00"),
    quantity = 10
)
