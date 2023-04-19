package com.montebruni.sales.domain.valueobjects

data class Document(val value: String) {

    init {
        if (!isValidCPF()) throw IllegalArgumentException("Invalid CPF")
    }

    private fun isValidCPF(): Boolean {
        val cpf = value.replace(Regex("[^\\d]"), "")

        if (cpf.length != 11 || cpf.all { it == cpf[0] }) return false

        return isValidDigit(cpf, 9) && isValidDigit(cpf, 10)
    }

    private fun isValidDigit(cpf: String, digit: Int): Boolean {
        var soma = 0

        for (i in 0 until digit) {
            soma += cpf[i].digitToInt() * ((digit + 1) - i)
        }

        var digitoVerificador = 11 - (soma % 11)

        if (digitoVerificador > 9) {
            digitoVerificador = 0
        }

        return cpf[digit].digitToInt() == digitoVerificador
    }
}
