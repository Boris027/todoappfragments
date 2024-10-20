package com.example.todoappremaster.ui

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoappremaster.data.Repository
import com.example.todoappremaster.data.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateTaskViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    private val _uiState= MutableStateFlow<updateTaskUiState>(updateTaskUiState.Loading)
    val uiState:StateFlow<updateTaskUiState> get() = _uiState.asStateFlow()
    var id:Int=-1

    init {
        viewModelScope.launch {
            repository.getStream().collect{
                tasks->if(tasks.find { c->c.id==id }==null){
                    _uiState.value=updateTaskUiState.Error("No se ha encontrado la tarea")
                }else{
                    _uiState.value=updateTaskUiState.Success(tasks.find { c->c.id==id }!!)
                }
            }
        }


    }

    //actualizar tarea
    fun updateTask(id:Int,task:Task){
        viewModelScope.launch {
            repository.update(id, task)
        }
    }

    fun setid(id:Int){
        this.id=id
    }





}

sealed class updateTaskUiState {
    data object Loading:updateTaskUiState()
    class Success(val task:Task):updateTaskUiState()
    class Error(val message:String):updateTaskUiState()
}




