package com.example.todoappremaster.data.source.remote

import com.example.todoappremaster.data.source.local.LocalTask

interface RemoteTaskDataSource {
    suspend fun requestAll():List<RemoteTask>
    suspend fun saveTasks(lista:List<RemoteTask>)

}