package com.montebruni.sales.infra.repository.postgresql.adapter

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.infra.repository.postgresql.createProductPostgresqlModel
import com.montebruni.sales.infra.repository.postgresql.port.ProductPostgresqlRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class ProductPostgresqlAdapterTest(
    @MockK private val productRepository: ProductPostgresqlRepository
) : UnitTests() {

    @InjectMockKs
    private lateinit var productAdapter: ProductPostgresqlAdapter

    @Test
    fun `should return a product entity when find by id`() {
        val expectedProduct = createProductPostgresqlModel()
        val productId = expectedProduct.id

        every { productRepository.findById(productId) } returns Optional.of(expectedProduct)

        val productEntity = productAdapter.findById(productId)

        assertEquals(expectedProduct.id, productEntity.id)

        verify { productRepository.findById(productId) }
    }

    @Test
    fun `should throw exception when try to find product with invalid id`() {
        val productId = UUID.randomUUID()

        every { productRepository.findById(productId) } returns Optional.empty()

        assertThrows<NoSuchElementException> { productAdapter.findById(productId) }

        verify { productRepository.findById(productId) }
    }
}
