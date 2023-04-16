package com.montebruni.sales.usecase.input

data class CreateOrderInput(
    val document: String,
    val products: List<OrderProductInput>,
    val coupon: String? = null
) {
    data class OrderProductInput(
        val description: String,
        val price: Double,
        val quantity: Int
    )
}
