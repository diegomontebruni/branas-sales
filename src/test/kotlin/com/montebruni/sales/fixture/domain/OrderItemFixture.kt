package com.montebruni.sales.fixture.domain

import com.montebruni.sales.application.domain.entity.Item

fun createOrderItem() = Item(
    product = createProduct(),
    quantity = 10
)
