package com.montebruni.sales.infra.repository.postgresql.model

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "order")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class OrderPostgresqlModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

    @Column(name = "order_number", nullable = false)
    val orderNumber: String,

    @Column(name = "document", nullable = false)
    val document: String,

    @Column(name = "total_amount", nullable = false)
    val totalAmount: Double,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    val coupon: CouponPostgresqlModel?,
)
