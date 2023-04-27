package com.montebruni.sales.resource.rest.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "The response of the checkout")
data class CreateCheckoutResponse(
    @Schema(description = "The order number", example = "AAAAPPPPPPPP")
    val orderNumber: String,
    @Schema(description = "The total amount of the order", example = "1200.99")
    val totalAmount: Double
)
