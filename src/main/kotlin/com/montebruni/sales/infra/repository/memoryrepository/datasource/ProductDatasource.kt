package com.montebruni.sales.infra.repository.memoryrepository.datasource

import com.montebruni.sales.infra.repository.memoryrepository.model.ProductMemoryRepositoryModel
import java.util.*

fun createProductDatasource() = listOf(
    ProductMemoryRepositoryModel(
        id = UUID.fromString("4bd2b777-b279-44e0-beb5-4e10b25d88ab"),
        description = "Camera",
        height = 20.0,
        width = 15.0,
        length = 10.0,
        weight = 1.0
    ),
    ProductMemoryRepositoryModel(
        id = UUID.fromString("9f65c1f0-ea34-414f-af4f-4b54bb2e7cec"),
        description = "Guitarra",
        height = 100.0,
        width = 30.0,
        length = 10.0,
        weight = 3.0
    ),
    ProductMemoryRepositoryModel(
        id = UUID.fromString("e3ef4b07-d115-4263-9c48-0a41b8f1a454"),
        description = "Geladeira",
        height = 200.0,
        width = 100.0,
        length = 50.0,
        weight = 40.0
    ),
)
