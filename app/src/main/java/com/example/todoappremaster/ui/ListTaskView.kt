package com.example.todoappremaster.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoappremaster.R
import com.example.todoappremaster.addtask
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
        val adapter= ListTaskRecyclerAdapter(array,::iravistadetail)
        binding.recyclerview.layoutManager=LinearLayoutManager(this.context)
        binding.recyclerview.adapter=adapter


        binding.buttonadd.setOnClickListener{
            val nav=findNavController()
            nav.navigate(R.id.action_listTaskView_to_addtask)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    fun iravistadetail(id:Int){
        Toast.makeText(this.context, id.toString(), Toast.LENGTH_SHORT).show()
    }


}