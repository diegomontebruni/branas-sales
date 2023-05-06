package com.montebruni.sales.infra.repository.postgresql.port

import com.montebruni.sales.infra.repository.postgresql.model.ProductPostgresqlModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductPostgresqlRepository : JpaRepository<ProductPostgresqlModel, UUID>
