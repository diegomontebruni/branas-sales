package com.montebruni.sales.infra.repository.postgresql.port

import com.montebruni.sales.infra.repository.postgresql.model.OrderPostgresqlModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OrderPostgresqlRepository : JpaRepository<OrderPostgresqlModel, UUID> {
    fun findTopByOrderByCreatedAtDesc(): OrderPostgresqlModel?
    fun findByOrderNumber(orderNumber: String): OrderPostgresqlModel?
}
