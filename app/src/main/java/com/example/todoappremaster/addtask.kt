package com.example.todoappremaster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoappremaster.databinding.FragmentAddtaskBinding
import com.example.todoappremaster.databinding.FragmentListTaskViewBinding

class addtask : Fragment() {

    private lateinit var binding:FragmentAddtaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddtaskBinding.inflate(inflater,container,false)

        


        return binding.root
    }


}