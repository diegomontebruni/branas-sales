package com.montebruni.sales.domain.port

import com.montebruni.sales.domain.entity.Product
import java.util.UUID

interface ProductRepository {
    fun findById(id: UUID): Product
}
