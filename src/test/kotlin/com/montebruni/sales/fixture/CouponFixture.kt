package com.montebruni.sales.fixture

import com.montebruni.sales.domain.entity.Coupon

fun createCoupon() = Coupon(code = "123", percentage = 10L)
