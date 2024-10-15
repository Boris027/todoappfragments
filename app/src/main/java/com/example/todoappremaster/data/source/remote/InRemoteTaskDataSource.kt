package com.example.todoappremaster.data.source.remote

import com.example.todoappremaster.data.source.local.LocalTask
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class InRemoteTaskDataSource @Inject constructor():RemoteTaskDataSource {

    private var _task=mutableListOf<RemoteTask>()

    override suspend fun requestAll(): List<RemoteTask> {
        delay(2000L)
        return _task.toList()
    }

    override suspend fun saveTasks(lista: List<RemoteTask>) {
        delay(2000L)
        _task=lista.toMutableList()

    }


}