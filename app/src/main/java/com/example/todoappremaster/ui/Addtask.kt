package com.example.todoappremaster.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.todoappremaster.data.AdapterTaskInMemory
import com.example.todoappremaster.data.Repository
import com.example.todoappremaster.data.Task
import com.example.todoappremaster.databinding.FragmentAddtaskBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class addtask : Fragment() {

    private lateinit var binding: FragmentAddtaskBinding
    @Inject lateinit var repository: Repository


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


        binding.buttonsave.setOnClickListener{
            val title:String=binding.title.text.toString()
            val body:String=binding.body.text.toString()
            val checked=binding.switchcompletado.isChecked
            viewLifecycleOwner.lifecycleScope.launch { withContext(Dispatchers.IO){
                repository.create(Task(1000, title, body, checked))
            } }
            val nav=findNavController()
            nav.popBackStack()
        }


        return binding.root
    }


}