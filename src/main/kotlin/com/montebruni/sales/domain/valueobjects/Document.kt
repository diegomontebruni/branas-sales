package com.montebruni.sales.domain.valueobjects

data class Document(val value: String) {

    init {
        if (!isValidCPF()) throw Exception("Invalid CPF")
    }

    private fun isValidCPF(): Boolean {
        return true
    }
}
