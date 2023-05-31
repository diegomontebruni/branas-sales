package com.montebruni.sales.infra.client.salescatalog

import com.montebruni.sales.infra.client.salescatalog.response.SalesCatalogResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.*

@FeignClient(
    url = "\${client.sales-catalog.product.host}",
    path = "/v1/products",
    name = "sales-catalog-product-client"
)
interface SalesCatalogClient {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): SalesCatalogResponse
}
