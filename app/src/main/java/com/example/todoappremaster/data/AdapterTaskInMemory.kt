package com.example.todoappremaster.data

class AdapterTaskInMemory private constructor():Repository{

    companion object{
        var instancia:AdapterTaskInMemory?=null
        fun getInstace():AdapterTaskInMemory{
            if(instancia==null){
                instancia= AdapterTaskInMemory()
            }
            return instancia!!
        }
    }

    private var _tasklist:MutableList<Task> = mutableListOf<Task>(
        Task(id = 1, title = "Titulo1", body = "Body1", completed = false),
        Task(id = 2, title = "Comer", body = "tengo que comer", completed = true),
        Task(id = 3, title = "Cenar", body = "tengo que cenar", completed = false)
    )

    override fun create(task: Task): Task {
        task.id=if (_tasklist.size==0) 1 else _tasklist.last().id+1
        _tasklist.add(task)
        return task
    }

    override fun getAll(): List<Task> {
        return _tasklist.toList()
    }

    override fun getOne(id: Int): Task {
        return _tasklist.find { c->c.id==id }!!
    }

    override fun update(id: Int, tarea: Task): Task {
        val taskIndex = _tasklist.indexOfFirst { it.id == id }

        if (taskIndex != -1) {
            _tasklist[taskIndex] = tarea
            return tarea
        }

        return tarea
    }





}