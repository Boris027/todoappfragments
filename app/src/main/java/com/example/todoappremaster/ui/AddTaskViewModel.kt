package com.example.todoappremaster.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoappremaster.data.Repository
import com.example.todoappremaster.data.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {


    fun createTask(task:Task){
        viewModelScope.launch {
            repository.create(task)
        }
    }


}