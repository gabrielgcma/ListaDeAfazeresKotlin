package com.example.listadeafazeres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var tarefaAdaptador: AdaptadorTarefa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tarefaAdaptador = AdaptadorTarefa(mutableListOf())

        rvLista.adapter = tarefaAdaptador
        rvLista.layoutManager = LinearLayoutManager(this)

        // definindo o que acontece quando os botoes sao clicados
        btnAddTarefa.setOnClickListener {
            val tarefaNome = etTarefaNome.text.toString()
            if(tarefaNome.isNotEmpty()) {
                val tarefa = Tarefa(tarefaNome)
                tarefaAdaptador.addTarefa(tarefa)
                // lima o campo de texto:
                etTarefaNome.text.clear()
            }
        }
        btnDelTarefa.setOnClickListener {
            tarefaAdaptador.delTarefasConcluidas()
        }
    }
}