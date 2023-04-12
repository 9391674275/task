package com.task.task.di

import com.task.task.model.RecyclerData
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("photos")
    fun getDataFromAPI(): Call<List<RecyclerData>?>
}