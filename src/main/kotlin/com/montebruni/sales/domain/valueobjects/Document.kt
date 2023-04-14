package com.montebruni.sales.domain.valueobjects

data class Document(val value: String) {

    init {
        if (!isValidCPF()) throw IllegalArgumentException("Invalid CPF")
    }

    private fun isValidCPF(): Boolean {
        val cpf = value.replace(".", "").replace("-", "")

        if (cpf.length != 11 || cpf.all { it == cpf[0] }) return false

        return isValidCPFFirstDigit(cpf) && isValidCPFSecondDigit(cpf)
    }

    private fun isValidCPFFirstDigit(cpf: String): Boolean {
        var soma = 0

        for (i in 0 until 9) {
            soma += cpf[i].digitToInt() * (10 - i)
        }

        var digitoVerificador = 11 - (soma % 11)

        if (digitoVerificador > 9) {
            digitoVerificador = 0
        }

        return cpf[9].digitToInt() == digitoVerificador
    }

    private fun isValidCPFSecondDigit(cpf: String): Boolean {
        var soma = 0

        for (i in 0 until 10) {
            soma += cpf[i].digitToInt() * (11 - i)
        }

        var digitoVerificador = 11 - (soma % 11)

        if (digitoVerificador > 9) {
            digitoVerificador = 0
        }

        return cpf[10].digitToInt() == digitoVerificador
    }
}
