package com.montebruni.sales.infra.client.salescatalog

import com.montebruni.sales.application.domain.entity.Product
import com.montebruni.sales.application.domain.port.ProductRepository
import com.montebruni.sales.application.domain.valueobjects.Amount
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SalesCatalogAdapter(
    @Autowired private val client: SalesCatalogClient
) : ProductRepository {

    override fun findById(id: UUID): Product = client.findById(id).let {
        Product(
            id = it.id,
            description = it.description,
            price = Amount(it.price)
        )
    }
}
