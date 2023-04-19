package com.montebruni.sales.extensions.repository.memoryrepository

import com.montebruni.sales.domain.entity.OrderItem
import com.montebruni.sales.domain.entity.Product
import com.montebruni.sales.domain.valueobjects.Amount
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderItemMemoryRepositoryModel

fun OrderItemMemoryRepositoryModel.toOrderItem(product: Product) = OrderItem(
    id = id,
    orderId = orderId,
    product = product,
    price = Amount(price),
    quantity = quantity
)
