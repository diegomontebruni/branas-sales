package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.domain.entity.Customer
import com.montebruni.sales.domain.port.CustomerRepository
import com.montebruni.sales.extensions.repository.memoryrepository.toCustomer
import com.montebruni.sales.infra.repository.memoryrepository.datasource.createCustomerDatasource
import java.util.UUID

class CustomerMemoryRepositoryImpl : CustomerRepository {

    private val customers = createCustomerDatasource()

    override fun findByDocument(document: String): Customer =
        customers.find { it.document == document }?.toCustomer() ?: throw IllegalArgumentException("Invalid customer")

    override fun findById(id: UUID): Customer =
        customers.find { it.id == id }?.toCustomer() ?: throw IllegalArgumentException("Invalid customer")
}
