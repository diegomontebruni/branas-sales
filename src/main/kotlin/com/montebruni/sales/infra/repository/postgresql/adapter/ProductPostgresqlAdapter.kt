package com.montebruni.sales.infra.repository.postgresql.adapter

import com.montebruni.sales.application.domain.entity.Product
import com.montebruni.sales.application.domain.port.ProductRepository
import com.montebruni.sales.extensions.repository.postgresql.toProduct
import com.montebruni.sales.infra.repository.postgresql.port.ProductPostgresqlRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductPostgresqlAdapter(
    @Autowired private val productRepository: ProductPostgresqlRepository
) : ProductRepository {

    override fun findById(id: UUID): Product = productRepository.findById(id).get().toProduct()
}