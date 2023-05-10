package com.montebruni.sales.fixture.usecase

import com.montebruni.sales.application.usecase.output.OrderOutput
import java.util.*

fun createOrderItemOutput() = OrderOutput.ItemOutput(
    id = UUID.randomUUID(),
    productId = UUID.randomUUID(),
    quantity = 1
)

fun createOrderOutput() = OrderOutput(
    id = UUID.randomUUID(),
    orderNumber = "202300000001",
    document = "48758578021",
    totalAmount = 10.0,
    items = listOf(createOrderItemOutput())
)
