package com.example.todoappremaster.di

import com.example.todoappremaster.data.AdapterTaskInMemory
import com.example.todoappremaster.data.Repository
import com.example.todoappremaster.data.source.local.InMemoryTaskDataSource
import com.example.todoappremaster.data.source.local.LocalTaskDataSource
import com.example.todoappremaster.data.source.remote.InRemoteTaskDataSource
import com.example.todoappremaster.data.source.remote.RemoteTaskDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTaskRepository(defaultTaskRepository: AdapterTaskInMemory):Repository

    @Binds
    @Singleton
    abstract fun memorytaskdata(defaultTaskRepository: InMemoryTaskDataSource):LocalTaskDataSource

    @Binds
    @Singleton
    abstract fun remotetaskdata(remoteTaskrepository:InRemoteTaskDataSource):RemoteTaskDataSource
}