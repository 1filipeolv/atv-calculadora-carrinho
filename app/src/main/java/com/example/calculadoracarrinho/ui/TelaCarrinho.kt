package com.example.calculadoracarrinho.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.calculadoracarrinho.domain.ItemCarrinho
import com.example.calculadoracarrinho.domain.ResumoCarrinho
import com.example.calculadoracarrinho.domain.emReais
import com.example.calculadoracarrinho.domain.resumo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCarrinho(itens: List<ItemCarrinho>) {
    val resumo = itens.resumo()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("🛒", style = MaterialTheme.typography.titleLarge)
                        Spacer(Modifier.width(8.dp))
                        Text("Meu Carrinho", style = MaterialTheme.typography.titleLarge)
                    }
                }
            )
        },
        bottomBar = { ResumoCard(resumo) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(Modifier.height(4.dp))
            itens.forEach { item ->
                ProdutoRow(
                    nome = item.produto.nome,
                    descricao = item.produto.descricao,
                    precoUnitario = item.produto.preco.emReais(),
                    quantidade = item.quantidade,
                    totalLinha = item.calcularTotal().emReais(),
                    descontoPercentual = item.produto.descontoPercentual
                )
            }
            Spacer(Modifier.height(4.dp))
        }
    }
}

@Composable
private fun ResumoCard(resumo: ResumoCarrinho) {
    Surface(tonalElevation = 3.dp, modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            LinhaResumo("Subtotal", resumo.subtotalBruto.emReais(), MaterialTheme.typography.bodyLarge)
            LinhaResumo("Descontos", "-" + resumo.descontos.emReais(), MaterialTheme.typography.bodyLarge)
            HorizontalDivider()
            LinhaResumo("TOTAL", resumo.totalFinal.emReais(), MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
private fun LinhaResumo(rotulo: String, valor: String, estilo: TextStyle) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(rotulo, style = estilo)
        Text(valor, style = estilo)
    }
}