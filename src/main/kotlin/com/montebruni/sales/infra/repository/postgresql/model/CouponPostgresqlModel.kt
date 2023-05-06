package com.montebruni.sales.infra.repository.postgresql.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "coupon")
data class CouponPostgresqlModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

    @Column(name = "code")
    val code: String,

    @Column(name = "percentage")
    val percentage: Int,

    @Column(name = "expiration_at")
    val expirationAt: Instant
)
