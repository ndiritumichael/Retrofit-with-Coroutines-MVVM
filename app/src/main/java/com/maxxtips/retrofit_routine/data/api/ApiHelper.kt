package com.maxxtips.retrofit_routine.data.api

class ApiHelper (private val responseService : ResponseService){
    suspend fun getUsers() = responseService.getUsers()
}