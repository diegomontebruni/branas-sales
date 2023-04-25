package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.application.domain.entity.Product
import com.montebruni.sales.application.domain.port.ProductRepository
import com.montebruni.sales.extensions.repository.memoryrepository.toProduct
import com.montebruni.sales.infra.repository.memoryrepository.port.ProductMemoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProductMemoryRepositoryAdapter(
    @Autowired private val productMemoryRepository: ProductMemoryRepository
) : ProductRepository {

    override fun findById(id: UUID): Product =
        productMemoryRepository.findById(id)?.toProduct() ?: throw IllegalArgumentException("Product not found")
}
