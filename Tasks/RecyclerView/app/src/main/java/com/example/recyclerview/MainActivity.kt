package com.example.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val data = listOf(cake("Vanilla","Vanilla",R.drawable.vanilla),
            cake("Unicorn","Unicorn",R.drawable.unicorn),
            cake("Carmello","Carmello",R.drawable.carmello),
            cake("BlackForest","BlackForest",R.drawable.blackforest)

        )


        val rv:RecyclerView = findViewById(R.id.cake)
        rv.adapter = CakeAdapter(data,this)
        rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


    }
}