package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.domain.entity.Customer
import com.montebruni.sales.domain.port.CustomerRepository
import com.montebruni.sales.extensions.repository.memoryrepository.toCustomer
import com.montebruni.sales.infra.repository.memoryrepository.datasource.createCustomerDatasource
import com.montebruni.sales.infra.repository.memoryrepository.port.CustomerMemoryRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CustomerMemoryRepositoryAdapter(
    private val customerMemoryRepository: CustomerMemoryRepository
) : CustomerRepository {

    private val customers = createCustomerDatasource()

    override fun findByDocument(document: String): Customer =
        customerMemoryRepository.findByDocument(document)?.toCustomer()
            ?: throw IllegalArgumentException("Invalid customer")

    override fun findById(id: UUID): Customer =
        customerMemoryRepository.findById(id)?.toCustomer()
            ?: throw IllegalArgumentException("Invalid customer")
}
