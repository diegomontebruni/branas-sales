package com.montebruni.sales.extensions.repository.memoryrepository

import com.montebruni.sales.domain.entity.OrderProduct
import com.montebruni.sales.domain.valueobjects.Amount
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderProductMemoryRepositoryModel

fun OrderProductMemoryRepositoryModel.toOrderProduct() = OrderProduct(
    id = id,
    description = description,
    price = Amount(price),
    quantity = quantity
)
