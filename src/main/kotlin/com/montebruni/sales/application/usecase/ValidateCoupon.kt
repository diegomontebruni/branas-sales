package com.montebruni.sales.application.usecase

import com.montebruni.sales.application.domain.port.CouponRepository
import com.montebruni.sales.application.usecase.input.ValidateCouponInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ValidateCoupon(
    @Autowired private val couponRepository: CouponRepository
) {

    fun execute(input: ValidateCouponInput): Boolean = couponRepository.findByCode(input.code).isValid()
}
