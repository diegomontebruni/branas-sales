package com.montebruni.sales.application.usecase

import com.montebruni.sales.application.domain.entity.Order
import com.montebruni.sales.application.domain.port.OrderRepository
import com.montebruni.sales.application.usecase.output.OrderOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FindOrderByOrderNumber(
    @Autowired private val orderRepository: OrderRepository
) {

    fun execute(orderNumber: String): OrderOutput = createOutput(orderRepository.findByOrderNumber(orderNumber))

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
