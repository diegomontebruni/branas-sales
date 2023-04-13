package com.montebruni.sales.extensions.repository.memoryrepository

import com.montebruni.sales.domain.entity.Product
import com.montebruni.sales.domain.valueobjects.Amount
import com.montebruni.sales.infra.repository.memoryrepository.model.ProductMemoryRepositoryModel

fun ProductMemoryRepositoryModel.toProduct() = Product(
    id = id,
    description = description,
    price = Amount(price),
    quantity = quantity
)
