package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.domain.entity.Product
import com.montebruni.sales.domain.port.ProductRepository
import com.montebruni.sales.extensions.repository.memoryrepository.toProduct
import com.montebruni.sales.infra.repository.memoryrepository.datasource.createProductDataSource
import java.util.*

class ProductMemoryRepository : ProductRepository {

    private val products = createProductDataSource()

    override fun findById(id: UUID): Product =
        products.find { it.id == id }?.toProduct() ?: throw IllegalArgumentException("Invalid product")
}
