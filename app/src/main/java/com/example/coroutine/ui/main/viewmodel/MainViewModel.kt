package com.example.coroutine.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.coroutine.data.repository.MainRepository
import com.example.coroutine.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.Loading(data = null))
        try {
            emit(Resource.Success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.Error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}