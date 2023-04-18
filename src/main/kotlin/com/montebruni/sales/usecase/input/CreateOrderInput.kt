package com.montebruni.sales.usecase.input

data class CreateOrderInput(
    val document: String,
    val items: List<ItemInput>,
    val coupon: String? = null
) {
    data class ItemInput(
        val description: String,
        val price: Double,
        val quantity: Int
    )
}
