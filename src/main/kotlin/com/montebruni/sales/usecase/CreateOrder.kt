package com.montebruni.sales.usecase

import com.montebruni.sales.domain.entity.Order
import com.montebruni.sales.domain.entity.OrderItem
import com.montebruni.sales.domain.port.CouponRepository
import com.montebruni.sales.domain.port.OrderRepository
import com.montebruni.sales.domain.valueobjects.Amount
import com.montebruni.sales.domain.valueobjects.Document
import com.montebruni.sales.usecase.input.CreateOrderInput
import com.montebruni.sales.usecase.output.CreateOrderOutput
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreateOrder(
    @Autowired private val orderRepository: OrderRepository,
    @Autowired private val couponRepository: CouponRepository
) {

    fun execute(input: CreateOrderInput) : CreateOrderOutput {
        logger.info { "Creating order for document: ${input.document}" }

        val orderId = UUID.randomUUID()
        val savedOrder = orderRepository.save(createOrderFromInput(input, orderId))

        logger.info { "Created order with id: $orderId" }

        return CreateOrderOutput(
            orderId = savedOrder.id,
            totalAmount = savedOrder.totalAmount.value.toDouble()
        )
    }

    private fun createOrderFromInput(input: CreateOrderInput, orderId: UUID): Order = Order(
        id = orderId,
        document = Document(input.document),
        items = input.items.map { createItemFromInput(it, orderId) },
        coupon = input.coupon?.let { getCoupon(it) }
    )

    private fun createItemFromInput(input: CreateOrderInput.ItemInput, orderId: UUID) = OrderItem(
        orderId = orderId,
        description = input.description,
        price = Amount(input.price),
        quantity = input.quantity
    )

    private fun getCoupon(couponCode: String) = couponRepository.findByCode(couponCode)

    companion object {
        val logger = KotlinLogging.logger { }
    }
}
