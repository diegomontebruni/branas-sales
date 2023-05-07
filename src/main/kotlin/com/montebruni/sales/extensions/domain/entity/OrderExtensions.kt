package com.montebruni.sales.extensions.domain.entity

import com.montebruni.sales.application.domain.entity.Order
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderMemoryRepositoryModel
import com.montebruni.sales.infra.repository.postgresql.model.OrderPostgresqlModel

fun Order.toOrderMemoryRepositoryModel() = OrderMemoryRepositoryModel(
    id = id,
    document = document.value,
    totalAmount = totalAmount.value.toDouble(),
    couponCode = coupon?.code,
    orderNumber = orderNumber.value
)

fun Order.toOrderPostgresqlModel() = OrderPostgresqlModel(
    id = id,
    orderNumber = orderNumber.value,
    document = document.value,
    totalAmount =  totalAmount.value.toDouble(),
    coupon = coupon?.toCouponPostgresqlModel(),
    orderItems = items.map { it.toOrderItemPostgresqlModel() }.toMutableList()
)
