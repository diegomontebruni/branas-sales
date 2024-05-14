package com.montebruni.sales.infra.client.salescatalog

import com.github.tomakehurst.wiremock.client.WireMock
import com.montebruni.sales.common.BaseHTTPClientIT
import com.montebruni.sales.common.stubGet
import com.montebruni.sales.infra.client.salescatalog.response.SalesCatalogResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.UUID

class SalesCatalogClientIT(
    @Autowired private val client: SalesCatalogClient
) : BaseHTTPClientIT() {

    private val baseUrl = "/v1/products"

    @Test
    fun `should successfully call catalog sales and return a product`() {
        val productId = UUID.randomUUID()
        val expectedResult = SalesCatalogResponse(
            id = UUID.randomUUID(),
            description = "description",
            price = 1.0,
        )
        val url = "$baseUrl/$productId"

        wmServer.stubGet(
            url = url,
            responseBody = mapper.writeValueAsString(expectedResult)
        )

        val result = client.findById(productId)

        assertEquals(expectedResult.id, result.id)
        assertEquals(expectedResult.description, result.description)
        assertEquals(expectedResult.price, result.price)

        wmServer.verify(
            WireMock.getRequestedFor(WireMock.urlEqualTo(url))
        )
    }
}
