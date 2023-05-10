package com.montebruni.sales.extensions.domain.entity

import com.montebruni.sales.application.domain.entity.Product
import com.montebruni.sales.infra.repository.postgresql.model.ProductPostgresqlModel

fun Product.toProductPostgresqlModel() = ProductPostgresqlModel(
    id = id,
    description = description,
    length = length.value,
    width = width.value,
    weight = weight.value,
    height = height.value
)
