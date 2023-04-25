package com.montebruni.sales.application.usecase.output

import java.util.UUID

data class CreateOrderOutput(val orderId: UUID, val totalAmount: Double)
