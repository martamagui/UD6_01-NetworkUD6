package com.marta.ud6_01_networkud6.provider

import com.marta.ud6_01_networkud6.model.TaskList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TaskApiService {
    @GET("api/list")
    fun getTaskLists(): Call<List<TaskList>>
}