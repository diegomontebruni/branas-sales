package com.montebruni.sales.infra.repository.postgresql.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "order_item")
data class OrderItemPostgresqlModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

    @Column(name = "order_id")
    val orderId: UUID,

    @Column(name = "product_id")
    val productId: UUID,

    @Column(name = "price")
    val price: Double,

    @Column(name = "quantity")
    val quantity: Int
)
