package com.example.coroutine.data.repository

import com.example.coroutine.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
}