package com.montebruni.sales.usecase

import com.montebruni.sales.domain.entity.Order
import com.montebruni.sales.domain.entity.OrderProduct
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
        orderRepository.save(createOrderFromInput(input, orderId))

        logger.info { "Created order with id: $orderId" }
        return CreateOrderOutput(orderId)
    }

    private fun createOrderFromInput(input: CreateOrderInput, orderId: UUID): Order = Order(
        id = orderId,
        document = Document(input.document),
        products = input.products.map { createOrderProductFromInput(it, orderId) },
        coupon = input.coupon?.let { getCoupon(it) }
    )

    private fun createOrderProductFromInput(input: CreateOrderInput.OrderProductInput, orderId: UUID) = OrderProduct(
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
