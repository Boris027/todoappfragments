package com.example.todoappremaster.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappremaster.R
import com.example.todoappremaster.data.Task
import com.example.todoappremaster.databinding.TaskcardviewBinding

class ListTaskRecyclerAdapter(private val lista:List<Task>, val iravistadetail:(id:Int)->Unit):RecyclerView.Adapter<ListTaskRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding:TaskcardviewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(task: Task){
            binding.title.text=task.title
            binding.body.text=task.body
            binding.switchcompleted.isChecked=task.completed
            binding.root.setOnClickListener{
                iravistadetail(task.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:TaskcardviewBinding=TaskcardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lista[position])
    }

}