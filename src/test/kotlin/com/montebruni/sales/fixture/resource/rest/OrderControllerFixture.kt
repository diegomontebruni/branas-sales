package com.montebruni.sales.fixture.resource.rest

import com.montebruni.sales.resource.rest.request.CalculateFreightRequest
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

fun createCalculateFreightRequest() = CalculateFreightRequest(
    items = listOf(
        CalculateFreightRequest.ItemRequest(
            quantity = 1,
            height = 20.0,
            width = 15.0,
            length = 10.0,
            weight = 1.0
        )
    )
)
