package com.example.todoappremaster.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoappremaster.R
import com.example.todoappremaster.data.AdapterTaskInMemory
import com.example.todoappremaster.data.Repository
import com.example.todoappremaster.data.Task
import com.example.todoappremaster.databinding.FragmentListTaskViewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class ListTaskView : Fragment() {

    private val viewModel:TaskListViewModel by viewModels()
    private lateinit var binding:FragmentListTaskViewBinding
    @Inject lateinit var repository: Repository


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentListTaskViewBinding.inflate(inflater,container,false)

        binding.buttonadd.setOnClickListener{
            val nav=findNavController()
            nav.navigate(R.id.action_listTaskView_to_addtask)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.uiState.collect {
                        uiState ->
                    when(uiState) {
                        is TaskListUiState.Error -> {
                            Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_SHORT).show()
                        }
                        TaskListUiState.Loading -> {
                            Toast.makeText(requireContext(), "Cargando contenido", Toast.LENGTH_SHORT).show()
                        }
                        is TaskListUiState.Success -> {
                            viewLifecycleOwner.lifecycleScope.launch {
                                val array=repository.getAll()
                                val adapter= ListTaskRecyclerAdapter(array,::iravistadetail,::changeswitchstate)
                                binding.recyclerview.layoutManager=LinearLayoutManager(context)
                                binding.recyclerview.adapter=adapter
                            }




                        }
                    }
                }
            }

        }
    }

    fun iravistadetail(id:Int){
        val nav=findNavController()
        val bundle:Bundle =Bundle()
        bundle.putInt("id",id)
        nav.navigate(R.id.action_listTaskView_to_updatetask,bundle)
    }

    fun changeswitchstate(id:Int,taskxd:Task){
        taskxd.completed=!taskxd.completed
        //Toast.makeText(requireContext(), taskxd.completed.toString(), Toast.LENGTH_SHORT).show()
        viewLifecycleOwner.lifecycleScope.launch {
        repository.update(id,taskxd)}
    }


}