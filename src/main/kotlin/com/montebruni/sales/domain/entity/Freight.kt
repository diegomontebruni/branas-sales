package com.montebruni.sales.domain.entity

import com.montebruni.sales.domain.valueobjects.PositiveDouble

data class Freight(
    val height: PositiveDouble,
    val width: PositiveDouble,
    val length: PositiveDouble,
    val weight: PositiveDouble,
)
