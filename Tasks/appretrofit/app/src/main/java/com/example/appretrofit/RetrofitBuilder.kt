package com.example.appretrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object{
        const val BaseUrl:String = "https://jsonplaceholder.typicode.com/"
        fun GetRetrofitBuilder(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}