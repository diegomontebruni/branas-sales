package com.montebruni.sales.fixture.resource.rest

import com.montebruni.sales.resource.rest.request.CreateCheckoutRequest
import java.util.*

fun createOrderRequest() = CreateCheckoutRequest(
    document = "55600547048",
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
