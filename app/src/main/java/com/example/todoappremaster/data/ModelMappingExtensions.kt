package com.example.todoappremaster.data

import com.example.todoappremaster.data.source.local.LocalTask

fun LocalTask.toExternal():Task = Task(
    this.id,
    this.title,
    this.body,
    this.completed
)

fun List<LocalTask>.toExternal():List<Task> = map(LocalTask::toExternal)


fun Task.toLocalTask():LocalTask =LocalTask(
    this.id,
    this.title,
    this.body,
    this.completed
)