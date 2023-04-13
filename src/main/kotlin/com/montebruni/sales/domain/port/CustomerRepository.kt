package com.montebruni.sales.domain.port

import com.montebruni.sales.domain.entity.Customer
import java.util.UUID

interface CustomerRepository {
    fun findByDocument(document: String): Customer
    fun findById(id: UUID): Customer
}
