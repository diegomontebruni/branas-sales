package com.montebruni.sales.extensions.repository.memoryrepository

import com.montebruni.sales.application.domain.entity.Coupon
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderMemoryRepositoryModel
import com.montebruni.sales.application.domain.entity.Order
import com.montebruni.sales.application.domain.entity.OrderItem
import com.montebruni.sales.application.domain.valueobjects.Amount
import com.montebruni.sales.application.domain.valueobjects.Document
import com.montebruni.sales.application.domain.valueobjects.OrderNumber

fun OrderMemoryRepositoryModel.toOrder(items: List<OrderItem>, coupon: Coupon?) = Order(
    id = id,
    document = Document(document),
    totalAmount = Amount(totalAmount),
    items = items,
    coupon = coupon,
    orderNumber = OrderNumber(orderNumber)
)
