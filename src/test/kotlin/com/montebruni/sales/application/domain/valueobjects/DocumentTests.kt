package com.montebruni.sales.application.domain.valueobjects

import com.montebruni.sales.application.domain.valueobjects.Document
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DocumentTests {

    @Test
    fun `should create document successfully`() {
        val input = "40369365062"

        assertEquals(input, Document(input).value)
    }

    @ParameterizedTest
    @ValueSource(strings = ["123", "1111111111", "12312312312", "0190293019222", "403693650621"])
    fun `should throw exception when document is not a valid CPF`(value: String) {
        assertThrows<IllegalArgumentException> { Document(value) }
    }
}
