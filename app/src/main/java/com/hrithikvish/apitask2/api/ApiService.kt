package com.hrithikvish.apitask2.api

import com.hrithikvish.apitask2.model.CustomerData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/Verify")
    fun getCustomerData(@Query("mobileno") mobileno: String): Call<CustomerData>
}