package com.example.listadeafazeres

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.tarefa_item.view.*

// classe com as principais logicas do app

class AdaptadorTarefa (
    // a lista de tarefas eh uma lista mutavel de Tarefas
    private val tarefas: MutableList<Tarefa>
    ) : RecyclerView.Adapter<AdaptadorTarefa.TarefaViewHolder>() {

    class TarefaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // o inflater pega o codigo xml e cria uma View que pode ser trabalhada no Kotlin
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        return TarefaViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.tarefa_item,
                parent,
                false
            )
        )
    }

    fun addTarefa(tarefa: Tarefa) {
        tarefas.add(tarefa)
        // tornar a nova tarefa visivel na tela:
        notifyItemInserted(tarefas.size-1)
    }

    fun delTarefasConcluidas() {
        // removendo somente aquelas com isChecked true:
        tarefas.removeAll { tarefa ->
            tarefa.isChecked
        }
        // tornar a mudança visivel na tela:
        notifyDataSetChanged()
    }

    private fun ligarTachado(tvTarefaNome: TextView, isChecked: Boolean) {
        // se a tarefa foi marcada como feita, tachamos o nome:
        if(isChecked) {
            tvTarefaNome.paintFlags = tvTarefaNome.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTarefaNome.paintFlags = tvTarefaNome.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefaAtual = tarefas[position]
        holder.itemView.apply {
            tvTarefaNome.text = tarefaAtual.nome
            cbFeito.isChecked = tarefaAtual.isChecked
            ligarTachado(tvTarefaNome, tarefaAtual.isChecked)
            cbFeito.setOnCheckedChangeListener { _, isChecked ->
                ligarTachado(tvTarefaNome, isChecked)
                tarefaAtual.isChecked = !tarefaAtual.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return tarefas.size
    }
}