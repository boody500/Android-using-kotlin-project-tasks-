package com.example.myapplication3

import android.os.Bundle

import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),Communicator {
    private var TAG:String = "Activity"
    private lateinit var btn: Button
    private lateinit var btnhide:Button
    private lateinit var dynamic:DynamicFregment
    private lateinit var static:StaticFregment
    private lateinit var printing:TextView
    private val manager= supportFragmentManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate: Activity")
        static = manager.findFragmentById(R.id.fragmentContainerView) as StaticFregment
        btn = findViewById(R.id.btnShow)
        btnhide = findViewById(R.id.btnhide)
        printing = findViewById(R.id.messsage)

        if (savedInstanceState == null){
            dynamic = DynamicFregment()
            val transaction= manager.beginTransaction()
            transaction.add(R.id.fragmentContainerView2,dynamic,"MyDynamicFregment")
            transaction.commit()
        }
        else{
            dynamic = manager.findFragmentByTag("MyDynamicFregment") as DynamicFregment
        }



        btnhide.setOnClickListener{

            val transaction= manager.beginTransaction()
            transaction.remove(dynamic)
            transaction.commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: Activity")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: Activity")

    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: Activity")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: Activity")
    }

    override fun respond(temp:String) {

        if (dynamic != null)
            dynamic = manager.findFragmentByTag("MyDynamicFregment") as DynamicFregment

        dynamic.update(temp)


        //return
    }
}