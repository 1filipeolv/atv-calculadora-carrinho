package com.example.calculadoracarrinho

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.example.calculadoracarrinho.data.carrinhoInicial
import com.example.calculadoracarrinho.domain.emReais
import com.example.calculadoracarrinho.domain.relatorioDescontos
import com.example.calculadoracarrinho.domain.resumo
import com.example.calculadoracarrinho.ui.TelaCarrinho

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        imprimirRelatorio()
        setContent {
            MaterialTheme {
                TelaCarrinho(itens = carrinhoInicial)
            }
        }
    }

    private fun imprimirRelatorio() {
        val tag = "RelatorioCarrinho"
        val resumo = carrinhoInicial.resumo()
        Log.d(tag, "===== PRODUTOS COM DESCONTO (maior -> menor) =====")
        carrinhoInicial.relatorioDescontos().forEach { Log.d(tag, it) }
        Log.d(tag, "Subtotal bruto : ${resumo.subtotalBruto.emReais()}")
        Log.d(tag, "Descontos      : ${resumo.descontos.emReais()}")
        Log.d(tag, "Total final    : ${resumo.totalFinal.emReais()}")
    }
}
