package com.maxxtips.retrofit_routine.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maxxtips.retrofit_routine.data.api.ApiHelper
import com.maxxtips.retrofit_routine.data.repository.MainRepository
import com.maxxtips.retrofit_routine.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}