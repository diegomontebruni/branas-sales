package com.montebruni.sales.fixture.domain

import com.montebruni.sales.application.domain.entity.Freight
import com.montebruni.sales.extensions.toPositiveDouble

fun createFreight() = Freight(
    height = 20.0.toPositiveDouble(),
    width = 15.0.toPositiveDouble(),
    length = 10.0.toPositiveDouble(),
    weight = 1.0.toPositiveDouble()
)
