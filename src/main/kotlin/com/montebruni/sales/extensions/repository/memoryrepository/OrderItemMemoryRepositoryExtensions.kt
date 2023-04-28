package com.montebruni.sales.extensions.repository.memoryrepository

import com.montebruni.sales.application.domain.entity.OrderItem
import com.montebruni.sales.application.domain.entity.Product
import com.montebruni.sales.application.domain.valueobjects.Amount
import com.montebruni.sales.extensions.toPositiveDouble
import com.montebruni.sales.infra.repository.memoryrepository.model.OrderItemMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.model.ProductMemoryRepositoryModel

fun OrderItemMemoryRepositoryModel.toOrderItem(product: ProductMemoryRepositoryModel) = OrderItem(
    id = id,
    orderId = orderId,
    product = Product(
        id = product.id,
        description = product.description,
        height = product.height.toPositiveDouble(),
        width = product.width.toPositiveDouble(),
        length = product.length.toPositiveDouble(),
        weight = product.weight.toPositiveDouble()
    ),
    price = Amount(price),
    quantity = quantity
)
