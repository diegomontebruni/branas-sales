package com.montebruni.sales.resource.rest

import com.montebruni.sales.application.usecase.ValidateCoupon
import com.montebruni.sales.application.usecase.input.ValidateCouponInput
import com.montebruni.sales.resource.rest.response.ValidateCouponResponse
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{code}/validate")
    fun validateCoupon(@PathVariable code: String): ValidateCouponResponse = ValidateCouponResponse(
        isValid = validateCoupon.execute(ValidateCouponInput(code))
    )
}
