package com.montebruni.sales.resource.rest

import com.montebruni.sales.application.usecase.ValidateCoupon
import com.montebruni.sales.application.usecase.input.ValidateCouponInput
import com.montebruni.sales.resource.rest.response.ValidateCouponResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/coupons")
class CouponController(
    private val validateCoupon: ValidateCoupon
) {

    @Operation(
        summary = "Validate Coupon",
        description = "Returns 200 with the response",
        tags = ["Coupon"]
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Coupon is valid or invalid"),
        ]
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{code}/validate")
    fun validateCoupon(
        @Schema(description = "Coupon Code", example = "DESC10")
        @PathVariable code: String
    ): ValidateCouponResponse = runCatching {
        validateCoupon.execute(ValidateCouponInput(code)).let {
            ValidateCouponResponse(isValid = true)
        }
    }.getOrElse { ValidateCouponResponse(isValid = false) }
}
