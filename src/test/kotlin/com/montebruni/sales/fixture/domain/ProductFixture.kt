package com.montebruni.sales.fixture.domain

import com.montebruni.sales.application.domain.entity.Product
import com.montebruni.sales.application.domain.valueobjects.Amount
import java.util.*

fun createProduct() = Product(
    id = UUID.randomUUID(),
    description = "Camera",
    price = Amount("10")
)
