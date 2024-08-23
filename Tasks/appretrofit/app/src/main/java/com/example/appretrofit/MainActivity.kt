package com.example.appretrofit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val s:SimpleService = RetrofitBuilder.GetRetrofitBuilder().create(SimpleService::class.java)

        val recyclerview:RecyclerView = findViewById(R.id.json)
        recyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        var call:Call<List<Data>> = s.getpost()

        call.enqueue(object : Callback<List<Data>> {
            override fun onResponse(p0: Call<List<Data>>, p1: Response<List<Data>>) {
                recyclerview.adapter = jsonAdapter(p1.body()!!)

            }

            override fun onFailure(p0: Call<List<Data>>, p1: Throwable) {

            }

        })
    }
}