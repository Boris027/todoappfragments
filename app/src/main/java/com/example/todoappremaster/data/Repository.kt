package com.example.todoappremaster.data

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun create(task:Task):Task
    suspend fun getAll():List<Task>
    suspend fun getOne(id:Int):Task
    suspend fun update(id:Int, tarea:Task):Task
    suspend fun getStream(): Flow<List<Task>>
}