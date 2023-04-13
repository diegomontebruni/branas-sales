package com.montebruni.sales.extensions.repository.memoryrepository

import com.montebruni.sales.domain.entity.Customer
import com.montebruni.sales.domain.valueobjects.Document
import com.montebruni.sales.infra.repository.memoryrepository.model.CustomerMemoryRepositoryModel

fun CustomerMemoryRepositoryModel.toCustomer() = Customer(
    id = id,
    name = name,
    document = Document(document)
)