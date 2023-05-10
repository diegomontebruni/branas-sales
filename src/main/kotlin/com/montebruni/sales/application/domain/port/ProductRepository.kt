package com.montebruni.sales.application.domain.port

import com.montebruni.sales.application.domain.entity.Product
import java.util.UUID

interface ProductRepository {
    fun findById(id: UUID): Product
}
