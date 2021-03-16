package com.maxxtips.retrofit_routine.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.maxxtips.retrofit_routine.data.repository.MainRepository
import com.maxxtips.retrofit_routine.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val mainRepository: MainRepository):ViewModel() {
    fun getUsers()= liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (e:Exception){
            emit(Resource.error(data = null,message = e.message?:"Error Occurred"))
        }
    }

}