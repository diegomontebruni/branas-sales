package com.montebruni.sales.infra.repository.postgresql.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "order")
data class OrderPostgresqlModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

    @Column(name = "order_number")
    val orderNumber: String,

    @Column(name = "document")
    val document: String,

    @Column(name = "total_amount")
    val totalAmount: Double,

    @Column(name = "coupon_code")
    val couponCode: String
)
