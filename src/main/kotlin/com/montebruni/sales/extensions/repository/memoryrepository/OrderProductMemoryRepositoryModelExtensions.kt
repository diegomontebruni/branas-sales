package com.montebruni.sales.extensions.repository.memoryrepository

import com.montebruni.sales.application.domain.entity.OrderItem
import com.montebruni.sales.application.domain.entity.Product
import com.montebruni.sales.application.domain.valueobjects.Amount
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderItemMemoryRepositoryModel

fun OrderItemMemoryRepositoryModel.toOrderItem(product: Product) = OrderItem(
    id = id,
    orderId = orderId,
    product = product,
    price = Amount(price),
    quantity = quantity
)
