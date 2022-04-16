package com.example.listadeafazeres

// uma data class em Kotlin eh uma classe que apenas guarda dados, e nao logica
// serve para preencher nossa view com dados

data class Tarefa (
    val nome: String,
    var isChecked: Boolean = false
)