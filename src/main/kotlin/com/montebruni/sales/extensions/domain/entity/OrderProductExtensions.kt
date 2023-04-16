package com.montebruni.sales.extensions.domain.entity

import com.montebruni.sales.domain.entity.OrderProduct
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderProductMemoryRepositoryModel

fun OrderProduct.toOrderProductMemoryRepositoryModel() = OrderProductMemoryRepositoryModel(
    id = id,
    orderId = orderId,
    description = description,
    price = price.value.toDouble(),
    quantity = quantity
)
