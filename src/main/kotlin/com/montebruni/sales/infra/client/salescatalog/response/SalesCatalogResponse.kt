package com.montebruni.sales.infra.client.salescatalog.response

import java.util.UUID

data class SalesCatalogResponse(
    val id: UUID,
    val description: String,
    val price: Double
)
