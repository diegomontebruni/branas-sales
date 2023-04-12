package com.montebruni.sales.extensions

import com.montebruni.sales.domain.valueobjects.Amount
import java.math.BigDecimal

fun BigDecimal.toAmount() = Amount(this)
