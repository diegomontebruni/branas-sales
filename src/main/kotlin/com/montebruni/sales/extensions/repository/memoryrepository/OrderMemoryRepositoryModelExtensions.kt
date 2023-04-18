package com.montebruni.sales.extensions.repository.memoryrepository

import com.montebruni.sales.domain.entity.Coupon
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderMemoryRepositoryModel
import com.montebruni.sales.domain.entity.Order
import com.montebruni.sales.domain.entity.OrderItem
import com.montebruni.sales.domain.valueobjects.Amount
import com.montebruni.sales.domain.valueobjects.Document

fun OrderMemoryRepositoryModel.toOrder(items: List<OrderItem>, coupon: Coupon) = Order(
    id = id,
    document = Document(document),
    totalAmount = Amount(totalAmount),
    items = items,
    coupon = coupon
)
