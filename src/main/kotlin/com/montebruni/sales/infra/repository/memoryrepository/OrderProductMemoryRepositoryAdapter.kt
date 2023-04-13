package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.domain.entity.OrderProduct
import com.montebruni.sales.domain.port.OrderProductRepository
import com.montebruni.sales.extensions.repository.memoryrepository.toOrderProduct
import com.montebruni.sales.infra.repository.memoryrepository.impl.OrderProductMemoryRepositoryImpl
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderProductMemoryRepositoryAdapter(
    private val orderProductMemoryRepositoryImpl: OrderProductMemoryRepositoryImpl
) : OrderProductRepository {

    override fun findById(id: UUID): OrderProduct =
        orderProductMemoryRepositoryImpl.findById(id)?.toOrderProduct()
            ?: throw IllegalArgumentException("Invalid product")
}
