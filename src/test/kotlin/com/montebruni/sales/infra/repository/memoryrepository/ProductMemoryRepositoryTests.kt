package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.infra.repository.memoryrepository.impl.ProductMemoryRepositoryImpl
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.util.*

class ProductMemoryRepositoryTests : UnitTests() {

    @InjectMockKs
    private lateinit var productMemoryRepositoryImpl: ProductMemoryRepositoryImpl

    @Test
    fun `should return a product successfully when has a valid id`() {
        val id = UUID.fromString("4bd2b777-b279-44e0-beb5-4e10b25d88ab")

        val product = productMemoryRepositoryImpl.findById(id)

        assertNotNull(product)
        assertEquals(id, product?.id)
    }

    @Test
    fun `should return null when has an invalid id`() {
        assertNull(productMemoryRepositoryImpl.findById(UUID.randomUUID()))
    }
}
