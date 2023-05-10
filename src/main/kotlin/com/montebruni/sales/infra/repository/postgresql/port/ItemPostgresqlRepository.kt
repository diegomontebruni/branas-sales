package com.montebruni.sales.infra.repository.postgresql.port

import com.montebruni.sales.infra.repository.postgresql.model.ItemPostgresqlModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ItemPostgresqlRepository : JpaRepository<ItemPostgresqlModel, UUID> {
    fun findByOrderId(orderId: UUID): List<ItemPostgresqlModel>?
}
