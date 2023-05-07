package com.montebruni.sales.extensions.repository.postgresql

import com.montebruni.sales.application.domain.entity.Product
import com.montebruni.sales.extensions.toPositiveDouble
import com.montebruni.sales.infra.repository.postgresql.model.ProductPostgresqlModel

fun ProductPostgresqlModel.toProduct() = Product(
    id = id,
    description = description,
    height = height.toPositiveDouble(),
    width = width.toPositiveDouble(),
    length = length.toPositiveDouble(),
    weight = weight.toPositiveDouble()
)
