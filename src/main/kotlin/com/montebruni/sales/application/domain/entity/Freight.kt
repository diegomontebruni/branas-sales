package com.montebruni.sales.application.domain.entity

import com.montebruni.sales.application.domain.valueobjects.PositiveDouble

data class Freight(
    val height: PositiveDouble,
    val width: PositiveDouble,
    val length: PositiveDouble,
    val weight: PositiveDouble,
)
