package br.com.alura.leilao.extensions


fun Double.formataParaDinheiro(): String {
    return "R$ ${String.format("%.2f", this)}"
}