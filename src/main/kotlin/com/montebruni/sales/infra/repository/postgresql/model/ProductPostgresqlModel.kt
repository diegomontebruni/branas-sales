package com.montebruni.sales.infra.repository.postgresql.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "products")
data class ProductPostgresqlModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

    @Column(name = "description")
    val description: String,

    @Column(name = "height")
    val height: Double,

    @Column(name = "width")
    val width: Double,

    @Column(name = "length")
    val length: Double,

    @Column(name = "weight")
    val weight: Double,

    @Column(name = "price")
    val price: Double,
)
