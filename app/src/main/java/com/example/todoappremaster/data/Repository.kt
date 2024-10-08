package com.example.todoappremaster.data

interface Repository {
    fun getAll():List<Task>
    fun getOne(id:Int):Task
    fun update(id:Int, tarea:Task):Task
}