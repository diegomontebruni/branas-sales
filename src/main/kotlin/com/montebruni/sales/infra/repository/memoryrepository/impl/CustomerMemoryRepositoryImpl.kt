package com.montebruni.sales.infra.repository.memoryrepository.impl

import com.montebruni.sales.infra.repository.memoryrepository.datasource.createCustomerDatasource
import com.montebruni.sales.infra.repository.memoryrepository.port.CustomerMemoryRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerMemoryRepositoryImpl : CustomerMemoryRepository {

    private val customers = createCustomerDatasource()

    override fun findByDocument(document: String) = customers.find { it.document == document }
    override fun findById(id: UUID) = customers.find { it.id == id }
}
