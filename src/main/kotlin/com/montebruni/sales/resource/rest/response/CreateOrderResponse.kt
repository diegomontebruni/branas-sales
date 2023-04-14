package com.montebruni.sales.resource.rest.response

import java.util.UUID

data class CreateOrderResponse(val orderId: UUID, val totalAmount: Double)
