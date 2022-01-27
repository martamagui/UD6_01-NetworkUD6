package com.marta.ud6_01_networkud6.provider

import com.marta.ud6_01_networkud6.model.Task
import com.marta.ud6_01_networkud6.model.TaskList
import retrofit2.Call
import retrofit2.http.*

interface TaskApiService {
    //Lists
    @GET("api/list")
    fun getTaskLists(): Call<List<TaskList>>

    @POST("api/list")
    fun addList(@Body taskList: TaskList): Call<Any>

    @DELETE("api/list/{listId}")
    fun deleteList(@Path("listId") listId: Int): Call<Any>

    //Tasks
    @GET("api/tasks")
    fun getTasks(): Call<List<Task>>

    @GET("api/tasks/{listId}")
    fun getTaskByListId(@Path("listId") listId: Int): Call<List<Task>>

    @POST("api/tasks")
    fun addTask(@Body task: Task): Call<Any>

    @DELETE("api/tasks/{listId}")
    fun deleteTask(@Path("listId") listId: Int): Call<Any>
}