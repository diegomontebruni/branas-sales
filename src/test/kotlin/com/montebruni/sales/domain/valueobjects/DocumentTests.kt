package com.montebruni.sales.domain.valueobjects

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DocumentTests {

    @Test
    fun `should create document successfully`() {
        val input = "40369365062"

        assertEquals(input, Document(input).value)
    }

    @Test
    fun `should throw exception when document is not a valid CPF`() {
        assertThrows<IllegalArgumentException> { Document("123") }
    }
}
