package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.domain.entity.Product
import com.montebruni.sales.domain.port.ProductRepository
import com.montebruni.sales.extensions.repository.memoryrepository.toProduct
import com.montebruni.sales.infra.repository.memoryrepository.datasource.createProductDataSource
import com.montebruni.sales.infra.repository.memoryrepository.impl.ProductMemoryRepositoryImpl
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProductMemoryRepositoryAdapter(
    private val productMemoryRepositoryImpl: ProductMemoryRepositoryImpl
) : ProductRepository {

    override fun findById(id: UUID): Product =
        productMemoryRepositoryImpl.findById(id)?.toProduct() ?: throw IllegalArgumentException("Invalid product")
}
