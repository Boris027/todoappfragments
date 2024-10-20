package com.example.todoappremaster.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoappremaster.data.AdapterTaskInMemory
import com.example.todoappremaster.data.Repository
import com.example.todoappremaster.data.Task
import com.example.todoappremaster.databinding.FragmentAddtaskBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class updatetask : Fragment() {

    private lateinit var binding: FragmentAddtaskBinding
    private val viewModel:UpdateTaskViewModel by viewModels()
    private var taskId:Int?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentAddtaskBinding.inflate(inflater, container, false)
        /*arguments?.let { bundle ->
            taskId = bundle.getInt("id", -1)  // Recupera el ID, -1 si no existe
        }*/
        binding.buttonback.setOnClickListener{
            val nav=findNavController()
            nav.popBackStack()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            taskId = bundle.getInt("id", -1)  // Recupera el ID, -1 si no existe
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.setid(taskId!!) //este carga el id de la tarea justo al iniciarse el viewmodel
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                        uiState ->
                    when(uiState) {
                        is updateTaskUiState.Error -> {
                            Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_SHORT).show()
                        }
                        updateTaskUiState.Loading -> {
                            Toast.makeText(requireContext(), "Cargando tarea", Toast.LENGTH_SHORT).show()
                        }
                        is updateTaskUiState.Success -> {
                            viewLifecycleOwner.lifecycleScope.launch {
                                val finaltask=uiState.task
                                if(finaltask!=null){
                                    binding.switchcompletado.isChecked=finaltask.completed
                                    binding.title.setText(finaltask.title)
                                    binding.body.setText(finaltask.body)
                                }
                                Toast.makeText(requireContext(), "Tarea cargada", Toast.LENGTH_SHORT).show()

                            }
                            binding.buttonsave.setOnClickListener{
                                val title:String=binding.title.text.toString()
                                val body:String=binding.body.text.toString()
                                val checked=binding.switchcompletado.isChecked


                                viewModel.updateTask(taskId!!,Task(taskId!!,title,body,checked))


                                val nav=findNavController()
                                //Toast.makeText(requireContext(), title, Toast.LENGTH_SHORT).show()
                                nav.popBackStack()
                            }




                        }
                    }
                }
            }

        }

    }


}