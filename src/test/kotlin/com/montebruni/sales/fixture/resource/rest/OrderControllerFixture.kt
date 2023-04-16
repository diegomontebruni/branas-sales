package com.montebruni.sales.fixture.resource.rest

import com.montebruni.sales.resource.rest.request.CreateOrderRequest

fun createOrderRequest() = CreateOrderRequest(
    document = "",
    products = listOf(
        CreateOrderRequest.OrderProductRequest(
            description = "Produto 1",
            price = 1.55,
            quantity = 1
        ),
        CreateOrderRequest.OrderProductRequest(
            description = "Produto 2",
            price = 2.50,
            quantity = 1
        ),
        CreateOrderRequest.OrderProductRequest(
            description = "Produto 3",
            price = 3.60,
            quantity = 1
        )
    )
)
