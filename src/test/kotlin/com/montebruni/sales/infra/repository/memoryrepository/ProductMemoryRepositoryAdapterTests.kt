package com.montebruni.sales.infra.repository.memoryrepository

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.infra.repository.memoryrepository.port.ProductMemoryRepository
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProductMemoryRepositoryAdapterTests(
    @MockK private val productMemoryRepository: ProductMemoryRepository
) : UnitTests() {

    @Test
    fun `should be implemented`() {
        TODO()

        assertEquals(1, 5)
    }
}