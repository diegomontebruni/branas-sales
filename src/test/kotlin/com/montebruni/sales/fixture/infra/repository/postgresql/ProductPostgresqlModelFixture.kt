package com.montebruni.sales.fixture.infra.repository.postgresql

import com.montebruni.sales.infra.repository.postgresql.model.ProductPostgresqlModel
import java.util.*

fun createProductPostgresqlModel() = ProductPostgresqlModel(
    id = UUID.randomUUID(),
    description = "Camera",
    height = 20.0,
    width = 15.0,
    length = 10.0,
    weight = 1.0
)
