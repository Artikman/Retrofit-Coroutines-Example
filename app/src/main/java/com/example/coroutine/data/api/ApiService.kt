package com.example.coroutine.data.api

import com.example.coroutine.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}