package com.example.todoappremaster.ui

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
class TaskListViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    private val _uiState = MutableStateFlow<TaskListUiState>(TaskListUiState.Loading)
    val uiState: StateFlow<TaskListUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getStream().collect{
                tasks -> if (tasks.isEmpty()){
                    _uiState.value=TaskListUiState.Error("No hay tareas disponibles")
                }else{
                    _uiState.value=TaskListUiState.Success(tasks)
                }

            }
        }
    }


     fun updateTaskCompletionStatus(id: Int) {
        viewModelScope.launch {
            try {
                val taskToUpdate = repository.getOne(id) // Asumiendo que tienes este método en tu repositorio
                taskToUpdate.completed=!taskToUpdate.completed
                repository.update(id, taskToUpdate)
            } catch (e: Exception) {
                _uiState.value = TaskListUiState.Error("Error al actualizar la tarea") // Maneja el error
            }
        }
    }

}

sealed class TaskListUiState {
    data object Loading:TaskListUiState()
    class Success(val tasks:List<Task>):TaskListUiState()
    class Error(val message:String):TaskListUiState()
}

