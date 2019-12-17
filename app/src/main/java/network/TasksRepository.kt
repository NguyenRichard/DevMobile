package network

import android.util.Log
import com.rng.tpapp.Task

class TasksRepository {
    private val tasksService = Api.INSTANCE.taskService

    suspend fun loadTasks(): List<Task>? {
        val tasksResponse = tasksService.getTasks()
        Log.e("loadTasks", tasksResponse.toString())
        return if (tasksResponse.isSuccessful) tasksResponse.body() else null
    }

    suspend fun deleteTask(task : Task) :  Boolean {
        val tasksResponse = tasksService.deleteTask(task.id)
        return tasksResponse.isSuccessful
    }

    suspend fun createTask(task : Task) : Boolean {
        val tasksResponse = tasksService.createTask(task)
        return tasksResponse.isSuccessful
    }
}