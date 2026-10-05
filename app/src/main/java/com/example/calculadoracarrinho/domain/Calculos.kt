package com.example.calculadoracarrinho.domain

import java.text.NumberFormat
import java.util.Locale
private fun arredondar(valor: Double): Double = Math.round(valor * 100.0) / 100.0

fun precoUnitarioFinal(produto: Produto): Double =
    arredondar(produto.preco * (1 - produto.descontoPercentual / 100.0))

fun subtotalBrutoDoItem(item: ItemCarrinho): Double =
    arredondar(item.produto.preco * item.quantidade)

fun descontoDoItem(item: ItemCarrinho): Double =
    arredondar(subtotalBrutoDoItem(item) * item.produto.descontoPercentual / 100.0)

fun totalFinalDoItem(item: ItemCarrinho): Double =
    arredondar(subtotalBrutoDoItem(item) - descontoDoItem(item))

data class ResumoCarrinho(
    val subtotalBruto: Double,
    val descontos: Double,
    val totalFinal: Double
)

fun List<ItemCarrinho>.resumo(): ResumoCarrinho {
    val bruto = sumOf { subtotalBrutoDoItem(it) }
    val desc = sumOf { descontoDoItem(it) }
    return ResumoCarrinho(arredondar(bruto), arredondar(desc), arredondar(bruto - desc))
}

fun List<ItemCarrinho>.relatorioDescontos(): List<String> =
    filter { it.produto.descontoPercentual > 0 }
        .sortedByDescending { it.calcularTotal() }
        .map { "${it.produto.nome} (-${it.produto.descontoPercentual}%) -> ${it.calcularTotal().emReais()}" }

fun Double.emReais(): String =
    NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)
