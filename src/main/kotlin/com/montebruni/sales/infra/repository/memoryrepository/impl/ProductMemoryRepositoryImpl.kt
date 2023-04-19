package com.montebruni.sales.infra.repository.memoryrepository.impl

import com.montebruni.sales.infra.repository.memoryrepository.datasource.createProductDatasource
import com.montebruni.sales.infra.repository.memoryrepository.model.ProductMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.port.ProductMemoryRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProductMemoryRepositoryImpl : ProductMemoryRepository {

    private val products = createProductDatasource()

    override fun findById(id: UUID): ProductMemoryRepositoryModel? = products.find { it.id == id }
}
