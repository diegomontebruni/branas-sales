package com.montebruni.sales.fixture.domain

import com.montebruni.sales.application.domain.entity.Product
import com.montebruni.sales.application.domain.valueobjects.PositiveDouble
import java.util.*

fun createProduct() = Product(
    id = UUID.randomUUID(),
    description = "Camera",
    height = PositiveDouble(20),
    width = PositiveDouble(15),
    length = PositiveDouble(10),
    weight = PositiveDouble(1)
)
