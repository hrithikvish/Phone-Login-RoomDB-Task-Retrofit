package com.hrithikvish.apitask2.api

import com.hrithikvish.apitask2.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private val retrofitInstance by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService by lazy {
        retrofitInstance.create(ApiService::class.java)
    }

}