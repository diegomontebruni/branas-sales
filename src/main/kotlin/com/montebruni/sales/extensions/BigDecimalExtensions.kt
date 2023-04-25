package com.montebruni.sales.extensions

import com.montebruni.sales.application.domain.valueobjects.Amount
import java.math.BigDecimal
import java.math.RoundingMode

fun BigDecimal.toAmount() = Amount(this.setScale(2, RoundingMode.HALF_EVEN))
