package com.example.myapplication3

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class StaticFregment : Fragment() {
    private var count:Int = 0
    private var TAG:String = "Fregment"
    private lateinit var btncount: Button
    private lateinit var showcount:TextView

    private lateinit var comm:Communicator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: Static Fregment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_static_fregment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: Static Fregment")
        btncount= view.findViewById(R.id.btncount)
        showcount = view.findViewById(R.id.textView2)
        btncount.setOnClickListener { updateCount() }
        comm = activity as Communicator
    }
    private fun updateCount(){
        this.count++
        comm.respond(count.toString())
    }





    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: haha")
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG, "onAttach: Static Fregment")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: Static Fregment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView: Static Fregment")
    }
}