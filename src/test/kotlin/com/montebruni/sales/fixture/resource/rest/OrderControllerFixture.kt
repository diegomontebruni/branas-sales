package com.montebruni.sales.fixture.resource.rest

import com.montebruni.sales.resource.rest.request.CreateOrderRequest
import java.util.*

fun createOrderRequest() = CreateOrderRequest(
    document = "",
    items = listOf(
        CreateOrderRequest.ItemRequest(
            productId = UUID.randomUUID(),
            price = 1.55,
            quantity = 1
        ),
        CreateOrderRequest.ItemRequest(
            productId = UUID.randomUUID(),
            price = 2.50,
            quantity = 1
        ),
        CreateOrderRequest.ItemRequest(
            productId = UUID.randomUUID(),
            price = 3.60,
            quantity = 1
        )
    )
)
