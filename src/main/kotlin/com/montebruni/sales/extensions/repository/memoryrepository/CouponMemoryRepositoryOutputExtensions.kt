package com.montebruni.sales.extensions.repository.memoryrepository

import com.montebruni.sales.domain.entity.Coupon
import com.montebruni.sales.infra.repository.memoryrepository.output.CouponMemoryRepositoryOutput

fun CouponMemoryRepositoryOutput.toCoupon() = Coupon(code = code, percentage = percentage)
