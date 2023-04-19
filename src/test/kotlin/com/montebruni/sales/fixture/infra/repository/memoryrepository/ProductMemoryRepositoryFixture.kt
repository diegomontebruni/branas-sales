package com.montebruni.sales.fixture.infra.repository.memoryrepository

import com.montebruni.sales.infra.repository.memoryrepository.model.ProductMemoryRepositoryModel
import java.util.*

fun createProductMemoryRepositoryModel() = ProductMemoryRepositoryModel(
    id = UUID.fromString("4bd2b777-b279-44e0-beb5-4e10b25d88ab"),
    description = "Camera",
    height = 20.0,
    width = 15.0,
    length = 10.0,
    weight = 1.0
)
