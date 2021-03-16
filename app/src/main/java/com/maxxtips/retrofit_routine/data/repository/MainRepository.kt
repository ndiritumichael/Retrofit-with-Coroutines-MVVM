package com.maxxtips.retrofit_routine.data.repository

import com.maxxtips.retrofit_routine.data.api.ApiHelper

class MainRepository(val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}