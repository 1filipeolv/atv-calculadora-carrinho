package com.example.calculadoracarrinho.domain

interface Pagavel {
    fun calcularTotal(): Double
}

data class Produto(
    val nome: String,
    val preco: Double,
    val descricao: String? = null,
    val descontoPercentual: Int = 0
) : Pagavel {
    override fun calcularTotal(): Double = precoUnitarioFinal(this)
}
data class ItemCarrinho(
    val produto: Produto,
    val quantidade: Int
) : Pagavel {
    override fun calcularTotal(): Double = totalFinalDoItem(this)
}
