package com.montebruni.sales.infra.repository.postgresql.port

import com.montebruni.sales.infra.repository.postgresql.model.OrderItemPostgresqlModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OrderItemPostgresqlRepository : JpaRepository<OrderItemPostgresqlModel, UUID> {
    fun findByOrderId(orderId: UUID): List<OrderItemPostgresqlModel>?
}
