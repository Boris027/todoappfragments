package com.example.todoappremaster.data.source.local

import com.example.todoappremaster.data.Task
import kotlinx.coroutines.flow.Flow

interface LocalTaskDataSource {
    suspend fun create(task: LocalTask): LocalTask
    suspend fun getAll():List<LocalTask>
    suspend fun getOne(id:Int): LocalTask
    suspend fun update(id:Int, tarea: LocalTask): LocalTask
    fun getStream(): Flow<List<LocalTask>>
}