package com.montebruni.sales.fixture

import com.montebruni.sales.domain.entity.Product
import com.montebruni.sales.domain.valueobjects.Amount

fun createProduct() = Product(description = "Teste", amount = Amount("10"), quantity = 3)
