package com.montebruni.sales.resource.rest.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Coupon validation response")
data class ValidateCouponResponse(
    @Schema(description = "Return true if is valid or false if is invalid")
    val isValid: Boolean
)
