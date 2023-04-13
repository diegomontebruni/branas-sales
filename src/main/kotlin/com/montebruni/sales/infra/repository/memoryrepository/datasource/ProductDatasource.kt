package com.montebruni.sales.infra.repository.memoryrepository.datasource

import com.montebruni.sales.infra.repository.memoryrepository.model.ProductMemoryRepositoryModel
import java.util.UUID

fun createProductDataSource() = listOf(
    ProductMemoryRepositoryModel(
        id = UUID.fromString("b8a10dc0-b45c-42c9-9165-6497ce3a2a5e"),
        description = "Phone",
        price = 9.99,
        quantity = 40
    ),
    ProductMemoryRepositoryModel(
        id = UUID.fromString("3434c612-8d54-4631-a9c7-4a0d561b0d6b"),
        description = "Mouse",
        price = 20.50,
        quantity = 30
    ),
    ProductMemoryRepositoryModel(
        id = UUID.fromString("18d74d96-8a55-4816-8726-f8be4c0dc851"),
        description = "Teclado",
        price = 35.55,
        quantity = 20
    ),
    ProductMemoryRepositoryModel(
        id = UUID.fromString("938c5360-8f4a-4fc3-b663-09e34c876c7e"),
        description = "Microfone",
        price = 250.00,
        quantity = 10
    ),
    ProductMemoryRepositoryModel(
        id = UUID.fromString("85c52385-a5cb-4bef-b42b-ef558a10ec05"),
        description = "Monitor",
        price = 1520.45,
        quantity = 5
    ),
)
