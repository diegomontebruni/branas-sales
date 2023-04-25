package com.montebruni.sales.extensions.repository.memoryrepository

import com.montebruni.sales.application.domain.entity.Product
import com.montebruni.sales.application.domain.valueobjects.PositiveDouble
import com.montebruni.sales.infra.repository.memoryrepository.model.ProductMemoryRepositoryModel

fun ProductMemoryRepositoryModel.toProduct() = Product(
    id = id,
    description = description,
    height = PositiveDouble(height),
    width = PositiveDouble(width),
    length = PositiveDouble(length),
    weight = PositiveDouble(weight)
)
