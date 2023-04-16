package com.montebruni.sales.fixture.usecase

import com.montebruni.sales.usecase.input.CreateOrderInput
import com.montebruni.sales.usecase.output.CreateOrderOutput
import java.util.*

fun createCreateOrderInput() = CreateOrderInput(
    document = "48758578021",
    products = listOf(
        CreateOrderInput.OrderProductInput(description = "Product 1", price = 1.00, quantity = 1),
        CreateOrderInput.OrderProductInput(description = "Product 2", price = 2.00, quantity = 1),
        CreateOrderInput.OrderProductInput(description = "Product 3", price = 3.55, quantity = 1)
    )
)

fun createCreateOrderWithCouponInput() = CreateOrderInput(
    document = "48758578021",
    products = listOf(
        CreateOrderInput.OrderProductInput(description = "Product 1", price = 1.00, quantity = 1),
        CreateOrderInput.OrderProductInput(description = "Product 2", price = 2.00, quantity = 1),
        CreateOrderInput.OrderProductInput(description = "Product 3", price = 3.34, quantity = 1)
    ),
    coupon = "123"
)

fun createCreateOrderOutput() = CreateOrderOutput(
    orderId = UUID.randomUUID(),
    totalAmount = 1.0
)
