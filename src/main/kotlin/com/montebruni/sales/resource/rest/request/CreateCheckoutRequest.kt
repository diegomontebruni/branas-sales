package com.montebruni.sales.resource.rest.request

import com.montebruni.sales.application.usecase.input.CreateOrderInput
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema(description = "create a checkout request")
data class CreateCheckoutRequest(
    @Schema(description = "The document of the client", example = "55600547048")
    val document: String,
    @Schema(description = "The list of items")
    val items: List<CheckoutItemRequest>,
    @Schema(description = "Valid coupon code", example = "DESC10")
    val coupon: String? = null
) {
    data class CheckoutItemRequest(
        @Schema(description = "The id of the product", example = "4bd2b777-b279-44e0-beb5-4e10b25d88ab")
        val productId: UUID,
        @Schema(description = "The quantity of the product", example = "10")
        val quantity: Int
    )
}

fun CreateCheckoutRequest.toCreateOrderInput() = CreateOrderInput(
    document = document,
    items = items.map {
        CreateOrderInput.ItemInput(
            productId = it.productId,
            quantity = it.quantity
        )
    },
    coupon = coupon
)
