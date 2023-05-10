package com.montebruni.sales.fixture.domain

import com.montebruni.sales.application.domain.entity.Item
import com.montebruni.sales.application.domain.valueobjects.Amount

fun createOrderItem() = Item(
    product = createProduct(),
    quantity = 10
)
