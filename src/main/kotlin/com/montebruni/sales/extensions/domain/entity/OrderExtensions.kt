package com.montebruni.sales.extensions.domain.entity

import com.montebruni.sales.domain.entity.Order
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderMemoryRepositoryModel

fun Order.toOrderMemoryRepositoryModel() = OrderMemoryRepositoryModel(
    id = id,
    document = document.value,
    totalAmount = totalAmount.value.toDouble(),
    couponCode = coupon?.code
)
