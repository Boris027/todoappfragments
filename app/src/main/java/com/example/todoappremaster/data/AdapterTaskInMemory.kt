package com.example.todoappremaster.data

import com.example.todoappremaster.data.source.local.LocalTask
import com.example.todoappremaster.data.source.local.LocalTaskDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

class AdapterTaskInMemory @Inject constructor(
    private val localTaskDataSource:LocalTaskDataSource
):Repository{





    suspend override fun create(task: Task): Task {
        val taskxd=localTaskDataSource.getAll()
        task.id=if (taskxd.size==0) 1 else taskxd.last().id+1


        localTaskDataSource.create(
            LocalTask(id = task.id, title = task.title, body = task.body, completed = task.completed))

        return task
    }

    suspend override fun getAll(): List<Task> {
        return localTaskDataSource.getAll().toExternal()
    }

    suspend override fun getOne(id: Int): Task {
        return (localTaskDataSource.getOne(id)).toExternal()
    }

    suspend override fun update(id: Int, tarea: Task): Task {


        localTaskDataSource.update(id,tarea.toLocalTask())
        return tarea
    }

    override suspend fun getStream(): Flow<List<Task>> {
        return localTaskDataSource.getStream().map { localTasks: List<LocalTask> ->
            localTasks.map { localTask ->
                Task(
                    id = localTask.id,
                    title = localTask.title,
                    body = localTask.body,
                    completed = localTask.completed
                )
            }
        }
    }


}