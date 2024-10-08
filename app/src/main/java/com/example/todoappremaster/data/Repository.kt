package com.example.todoappremaster.data

interface Repository {
    fun create(task:Task):Task
    fun getAll():List<Task>
    fun getOne(id:Int):Task
    fun update(id:Int, tarea:Task):Task
}