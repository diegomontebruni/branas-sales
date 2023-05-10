package com.montebruni.sales.infra.repository.postgresql.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "items")
data class OrderItemPostgresqlModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    val product: ProductPostgresqlModel,

    @Column(name = "order_id")
    val orderId: UUID,

    @Column(name = "price")
    val price: Double,

    @Column(name = "quantity")
    val quantity: Int
)
