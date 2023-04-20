package com.montebruni.sales.usecase

import com.montebruni.sales.domain.entity.Coupon
import com.montebruni.sales.domain.entity.Order
import com.montebruni.sales.domain.entity.OrderItem
import com.montebruni.sales.domain.entity.Product
import com.montebruni.sales.domain.port.CouponRepository
import com.montebruni.sales.domain.port.OrderRepository
import com.montebruni.sales.domain.port.ProductRepository
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
    @Autowired private val couponRepository: CouponRepository,
    @Autowired private val productRepository: ProductRepository,
) {

    fun execute(input: CreateOrderInput) : CreateOrderOutput {
        logger.info { "Creating order for document: ${input.document}" }

        val orderId = Order.generateId()
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
        coupon = input.coupon?.let { findCoupon(it) }
    )

    private fun createItemFromInput(input: CreateOrderInput.ItemInput, orderId: UUID) = OrderItem(
        orderId = orderId,
        product = findProduct(input.productId),
        price = Amount(input.price),
        quantity = input.quantity
    )

    private fun findProduct(productId: UUID): Product = productRepository.findById(productId)

    private fun findCoupon(couponCode: String): Coupon = couponRepository.findByCode(couponCode).let {
        if (it.isValid()) return it
        throw IllegalArgumentException("Expired coupon")
    }

    companion object {
        val logger = KotlinLogging.logger { }
    }
}
