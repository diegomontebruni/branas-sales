package com.montebruni.sales.fixture.usecase

import com.montebruni.sales.application.domain.valueobjects.OrderNumber
import com.montebruni.sales.application.usecase.input.CreateOrderInput
import com.montebruni.sales.application.usecase.output.CreateOrderOutput
import java.util.*

fun createCreateOrderInput() = CreateOrderInput(
    document = "48758578021",
    items = listOf(
        CreateOrderInput.ItemInput(productId = UUID.randomUUID(), quantity = 1),
        CreateOrderInput.ItemInput(productId = UUID.randomUUID(), quantity = 1),
        CreateOrderInput.ItemInput(productId = UUID.randomUUID(), quantity = 1)
    )
)

fun createCreateOrderWithCouponInput() = CreateOrderInput(
    document = "48758578021",
    items = listOf(
        CreateOrderInput.ItemInput(productId = UUID.randomUUID(), quantity = 1),
        CreateOrderInput.ItemInput(productId = UUID.randomUUID(), quantity = 1),
        CreateOrderInput.ItemInput(productId = UUID.randomUUID(), quantity = 1)
    ),
    coupon = "123"
)

fun createCreateOrderOutput() = CreateOrderOutput(
    orderNumber = OrderNumber().value,
    totalAmount = 1.0
)
