package com.maxxtips.retrofit_routine.data.api

import com.maxxtips.retrofit_routine.data.model.User
import retrofit2.http.GET

interface ResponseService {
    @GET("users")
    suspend fun getUsers():List<User>

}