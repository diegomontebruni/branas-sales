package com.montebruni.sales.infra.client.salescatalog

import com.montebruni.sales.common.UnitTests
import com.montebruni.sales.infra.client.salescatalog.response.SalesCatalogResponse
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.UUID

class SalesCatalogAdapterTest(
    @MockK private val client: SalesCatalogClient
) : UnitTests() {

    @InjectMockKs
    private lateinit var salesCatalog: SalesCatalogAdapter

    @Test
    fun `should successfully call catalog sales and return a product`() {
        val productId = UUID.randomUUID()
        val response = SalesCatalogResponse(
            id = UUID.randomUUID(),
            description = "description",
            price = 10.0,
        )

        every { client.findById(productId) } returns response

        val result = salesCatalog.findById(productId)

        assertEquals(result.id, response.id)
        assertEquals(result.description, response.description)

        verify { client.findById(productId) }
    }
}
