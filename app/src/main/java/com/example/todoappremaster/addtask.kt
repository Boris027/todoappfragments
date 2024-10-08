package com.example.todoappremaster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.todoappremaster.data.AdapterTaskInMemory
import com.example.todoappremaster.data.Task
import com.example.todoappremaster.databinding.FragmentAddtaskBinding
import com.example.todoappremaster.databinding.FragmentListTaskViewBinding

class addtask : Fragment() {

    private lateinit var binding:FragmentAddtaskBinding
    private val repository=AdapterTaskInMemory.getInstace()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddtaskBinding.inflate(inflater,container,false)

        binding.buttonback.setOnClickListener{
            val nav=findNavController()
            nav.popBackStack()
        }


        binding.buttonsave.setOnClickListener{
            val title:String=binding.title.text.toString()
            val body:String=binding.body.text.toString()
            val checked=binding.switchcompletado.isChecked
            repository.create(Task(1000,title,body,checked))
            val nav=findNavController()
            nav.popBackStack()
        }


        return binding.root
    }


}