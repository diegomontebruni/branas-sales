package com.montebruni.sales.infra.repository.memoryrepository.datasource

import com.montebruni.sales.infra.repository.memoryrepository.model.CustomerMemoryRepositoryModel
import java.util.UUID

fun createCustomerDatasource() = listOf(
    CustomerMemoryRepositoryModel(
        id = UUID.fromString("ba7e4110-1778-425f-b1eb-d7ef6535827e"),
        name = "Customer 1",
        document = "76568335010"
    ),
    CustomerMemoryRepositoryModel(
        id = UUID.fromString("734f8dfa-800d-4688-9e10-85eb17a19c84"),
        name = "Customer 2",
        document = "59832343070"
    ),
    CustomerMemoryRepositoryModel(
        id = UUID.fromString("2d6827d4-2376-4ae9-904f-57649fdcbcf7"),
        name = "Customer 3",
        document = "50306163063"
    ),
    CustomerMemoryRepositoryModel(
        id = UUID.fromString("58c4dd6f-f4d4-4f00-af00-be849ef60a0b"),
        name = "Customer 4",
        document = "74297590093"
    ),
    CustomerMemoryRepositoryModel(
        id = UUID.fromString("3c1e21e0-bbac-4e4e-99ab-2102a76cb4f4"),
        name = "Customer 5",
        document = "91971002003"
    )
)
