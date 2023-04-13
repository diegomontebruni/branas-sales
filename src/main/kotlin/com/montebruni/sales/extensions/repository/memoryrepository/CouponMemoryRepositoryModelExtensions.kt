package com.montebruni.sales.extensions.repository.memoryrepository

import com.montebruni.sales.domain.entity.Coupon
import com.montebruni.sales.infra.repository.memoryrepository.model.CouponMemoryRepositoryModel

fun CouponMemoryRepositoryModel.toCoupon() = Coupon(code = code, percentage = percentage)
