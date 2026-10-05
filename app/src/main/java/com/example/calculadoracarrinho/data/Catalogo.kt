package com.example.calculadoracarrinho.data

import com.example.calculadoracarrinho.domain.ItemCarrinho
import com.example.calculadoracarrinho.domain.Produto

val catalogo: List<Produto> = listOf(
    Produto(
        nome = "Notebook Dell Inspiron 15 3520 Intel Core i5 16GB RAM 512GB SSD",
        preco = 3499.00,
        descricao = "Um notebook rápido para trabalho, estudos e entretenimento, com tela Full HD e bateria de longa duração.",
        descontoPercentual = 5
    ),
    Produto(nome = "Mouse sem fio", preco = 89.90, descricao = null),
    Produto(nome = "Teclado mecânico RGB", preco = 349.90, descricao = "Switch azul, ABNT2"),
    Produto("Monitor 27\" 144Hz", 1299.90, "Painel IPS, 1ms, FreeSync", descontoPercentual = 10),
    Produto("Headset Gamer 7.1", 259.90, "Som surround virtual com microfone removível"),
    Produto("Webcam Full HD", 189.90, null)
)

private fun produto(nome: String) = catalogo.first { it.nome.startsWith(nome) }
val carrinhoInicial: List<ItemCarrinho> = listOf(
    ItemCarrinho(produto("Notebook Dell Inspiron"), 2),
    ItemCarrinho(produto("Mouse sem fio"), 1),
    ItemCarrinho(produto("Teclado mecânico RGB"), 1)
)
