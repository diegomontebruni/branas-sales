package com.montebruni.sales.application.usecase

import com.montebruni.sales.application.domain.entity.Order
import com.montebruni.sales.application.domain.entity.OrderItem
import com.montebruni.sales.application.domain.port.CouponRepository
import com.montebruni.sales.application.domain.port.OrderRepository
import com.montebruni.sales.application.domain.port.ProductRepository
import com.montebruni.sales.application.domain.valueobjects.Amount
import com.montebruni.sales.application.domain.valueobjects.Document
import com.montebruni.sales.application.domain.valueobjects.OrderNumber
import com.montebruni.sales.application.usecase.input.CreateOrderInput
import com.montebruni.sales.application.usecase.output.CreateOrderOutput
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreateOrder(
    @Autowired private val orderRepository: OrderRepository,
    @Autowired private val couponRepository: CouponRepository,
    @Autowired private val productRepository: ProductRepository,
) {

    fun execute(input: CreateOrderInput) : CreateOrderOutput {
        logger.info { "Creating order for document: ${input.document}" }

        val order = createOrderFromInput(input).also { orderRepository.save(it) }

        logger.info { "Created order with number: ${order.orderNumber.value}" }

        return CreateOrderOutput(
            orderNumber = order.orderNumber.value,
            totalAmount = order.totalAmount.value.toDouble()
        )
    }

    private fun createOrderFromInput(input: CreateOrderInput): Order {
        val orderId = Order.generateId()

        return Order(
            id = orderId,
            orderNumber = orderRepository.getLastOrderNumber()?.let { OrderNumber(it).increment() } ?: OrderNumber(),
            document = Document(input.document),
            items = input.items.map { createItemFromInput(it, orderId) },
            coupon = input.coupon?.let { couponRepository.findByCode(it).throwIfExpired() }
        )
    }

    private fun createItemFromInput(input: CreateOrderInput.ItemInput, orderId: UUID) = OrderItem(
        product = productRepository.findById(input.productId),
        price = Amount(input.price),
        quantity = input.quantity
    )

    companion object {
        val logger = KotlinLogging.logger { }
    }
}
