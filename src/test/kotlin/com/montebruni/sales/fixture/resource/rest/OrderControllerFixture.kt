package com.montebruni.sales.fixture.resource.rest

import com.montebruni.sales.resource.rest.request.CalculateFreightRequest
import com.montebruni.sales.resource.rest.request.CreateCheckoutRequest
import java.util.*

fun createOrderRequest() = CreateCheckoutRequest(
    document = "",
    items = listOf(
        CreateCheckoutRequest.CheckoutItemRequest(
            productId = UUID.randomUUID(),
            quantity = 1
        ),
        CreateCheckoutRequest.CheckoutItemRequest(
            productId = UUID.randomUUID(),
            quantity = 1
        ),
        CreateCheckoutRequest.CheckoutItemRequest(
            productId = UUID.randomUUID(),
            quantity = 1
        )
    )
)

fun createCalculateFreightRequest() = CalculateFreightRequest(
    cep = "12341241",
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
