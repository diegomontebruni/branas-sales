package com.montebruni.sales.infra.repository.memoryrepository.impl

import com.montebruni.sales.infra.repository.memoryrepository.model.OrderProductMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.port.OrderProductMemoryRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderProductMemoryRepositoryImpl : OrderProductMemoryRepository {

    private val orderProducts = mutableListOf<OrderProductMemoryRepositoryModel>()

    override fun save(orderProductModel: OrderProductMemoryRepositoryModel): OrderProductMemoryRepositoryModel =
        orderProducts.add(orderProductModel).let { orderProductModel }

    override fun findById(id: UUID): OrderProductMemoryRepositoryModel? = orderProducts.find { it.id == id }

    override fun findByOrderId(orderId: UUID): List<OrderProductMemoryRepositoryModel> =
        orderProducts.filter { it.orderId == orderId }
}
