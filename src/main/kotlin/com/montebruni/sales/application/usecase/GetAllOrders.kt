package com.montebruni.sales.application.usecase

import com.montebruni.sales.application.domain.entity.Order
import com.montebruni.sales.application.domain.port.OrderRepository
import com.montebruni.sales.application.usecase.output.OrderOutput
import org.springframework.stereotype.Service

@Service
class GetAllOrders(
    private val orderRepository: OrderRepository
) {

    fun execute(): List<OrderOutput> = orderRepository.getOrders()?.map { createOutput(it) } ?: emptyList()

    private fun createOutput(order: Order): OrderOutput = OrderOutput(
        id = order.id,
        orderNumber = order.orderNumber.value,
        document = order.document.value,
        totalAmount = order.totalAmount.value.toDouble(),
        items = order.items.map {
            OrderOutput.ItemOutput(
                id = it.id,
                productId = it.product.id,
                quantity = it.quantity
            )
        },
        coupon = order.coupon?.code
    )
}
