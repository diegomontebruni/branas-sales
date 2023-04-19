package com.montebruni.sales.extensions.repository.memoryrepository

import com.montebruni.sales.domain.entity.Product
import com.montebruni.sales.domain.valueobjects.PositiveDouble
import com.montebruni.sales.infra.repository.memoryrepository.model.ProductMemoryRepositoryModel

fun ProductMemoryRepositoryModel.toProduct() = Product(
    id = id,
    description = description,
    height = PositiveDouble(height),
    width = PositiveDouble(width),
    length = PositiveDouble(length),
    weight = PositiveDouble(weight)
)
