package com.example.todoappremaster.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todoappremaster.data.AdapterTaskInMemory
import com.example.todoappremaster.data.Task
import com.example.todoappremaster.databinding.FragmentAddtaskBinding

class updatetask : Fragment() {

    private lateinit var binding: FragmentAddtaskBinding
    private val repository= AdapterTaskInMemory.getInstace()
    private var taskId:Int?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentAddtaskBinding.inflate(inflater, container, false)

        binding.buttonback.setOnClickListener{
            val nav=findNavController()
            nav.popBackStack()
        }

        arguments?.let { bundle ->
            taskId = bundle.getInt("id", -1)  // Recupera el ID, -1 si no existe
        }


        val task=repository.getOne(taskId!!)
        binding.switchcompletado.isChecked=task.completed
        binding.title.setText(task.title)
        binding.body.setText(task.body)

        binding.buttonsave.setOnClickListener{
            val title:String=binding.title.text.toString()
            val body:String=binding.body.text.toString()
            val checked=binding.switchcompletado.isChecked
            repository.update(taskId!!,Task(taskId!!, title, body, checked))
            val nav=findNavController()
            //Toast.makeText(requireContext(), title, Toast.LENGTH_SHORT).show()
            println(repository.getAll())
            nav.popBackStack()
        }




        return binding.root
    }


}