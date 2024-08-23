package com.example.appretrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface SimpleService {

    @GET("posts")
    fun getpost():Call<List<Data>>


}