package com.montebruni.sales.infra.repository.postgresql.port

import com.montebruni.sales.infra.repository.postgresql.model.CouponPostgresqlModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CouponPostgresqlRepository : JpaRepository<CouponPostgresqlModel, UUID> {
    fun findByCode(code: String): CouponPostgresqlModel
}
