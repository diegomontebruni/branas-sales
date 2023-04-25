package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.fixture.infra.repository.memoryrepository.createProductMemoryRepositoryModel
import com.montebruni.sales.infra.repository.memoryrepository.port.ProductMemoryRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class ProductMemoryRepositoryAdapterTests(
    @MockK private val productMemoryRepository: ProductMemoryRepository
) : UnitTests() {

    @InjectMockKs
    private lateinit var productMemoryRepositoryAdapter: ProductMemoryRepositoryAdapter

    @AfterEach
    internal fun tearDown() {
        confirmVerified(productMemoryRepository)
    }

    @Test
    fun `should returns product when has a valid id`() {
        val id = UUID.randomUUID()
        val output = createProductMemoryRepositoryModel().copy(id = id)

        every { productMemoryRepository.findById(id) } returns output

        val product = productMemoryRepositoryAdapter.findById(id)

        assertEquals(id, product.id)

        verify { productMemoryRepository.findById(id) }
    }

    @Test
    fun `should throw exception when has a invalid id`() {
        val id = UUID.randomUUID()

        every { productMemoryRepository.findById(id) } returns null

        assertThrows<IllegalArgumentException> { productMemoryRepositoryAdapter.findById(id) }

        verify { productMemoryRepository.findById(id) }
    }
}
