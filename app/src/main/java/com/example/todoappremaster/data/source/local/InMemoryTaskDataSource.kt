package com.example.todoappremaster.data.source.local

import com.example.todoappremaster.data.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class InMemoryTaskDataSource @Inject constructor():LocalTaskDataSource{

    private val _task:MutableList<LocalTask> = mutableListOf<LocalTask>(
        LocalTask(id = 1, title = "Titulo1", body = "Body1", completed = false),
        LocalTask(id = 2, title = "Comer", body = "tengo que comer", completed = true),
        LocalTask(id = 3, title = "Cenar", body = "tengo que cenar", completed = false)
    )
    private val _taskStream= MutableStateFlow<List<LocalTask>>(_task.toList())

    override suspend fun create(task: LocalTask): LocalTask {
        _task.add(task)
        _taskStream.value=_task.toList()
        return task
    }

    override suspend fun getAll(): List<LocalTask> {
        return _task.toList()
    }

    override suspend fun getOne(id: Int): LocalTask {
        return _task.find { c->c.id==id }!!
    }

    override suspend fun update(id: Int, tarea: LocalTask): LocalTask {
        val taskIndex = _task.indexOfFirst { it.id == id }

        if (taskIndex != -1) {
            _task[taskIndex] = tarea
        }
        _taskStream.value=_task.toList()
        return tarea
    }

    override fun getStream(): Flow<List<LocalTask>> {
        return _taskStream
    }

}