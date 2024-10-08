package com.example.todoappremaster.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoappremaster.R
import com.example.todoappremaster.data.AdapterTaskInMemory
import com.example.todoappremaster.databinding.FragmentListTaskViewBinding


class ListTaskView : Fragment() {

    private lateinit var binding:FragmentListTaskViewBinding
    private val repository:AdapterTaskInMemory=AdapterTaskInMemory.getInstace()

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentListTaskViewBinding.inflate(inflater,container,false)

        val array=repository.getAll()
        val adapter= ListTaskRecyclerAdapter(array)
        binding.recyclerview.layoutManager=LinearLayoutManager(this.context)
        binding.recyclerview.adapter=adapter

        // Inflate the layout for this fragment
        return binding.root
    }

    fun saytoast(){
        Toast.makeText(this.context, "xd", Toast.LENGTH_SHORT).show()
    }


}