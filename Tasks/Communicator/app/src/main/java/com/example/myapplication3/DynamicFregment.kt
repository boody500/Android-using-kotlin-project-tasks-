package com.example.myapplication3

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class DynamicFregment : Fragment() {

    lateinit var ref:Communicator
    lateinit var text:TextView
    private val handler = Handler(Looper.getMainLooper())


//    private val updateTextRunnable = object : Runnable {
//        override fun run() {
//            text.text = ref.respond()
//            handler.postDelayed(this, 50)
//        }
//    }

    fun update(temp:String){
        text.text = temp
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dynamic_fregment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ref = activity as Communicator
        text = view.findViewById(R.id.textView4)


    }

    override fun onResume() {
        super.onResume()
        //handler.post(updateTextRunnable)
    }

    override fun onPause() {
        super.onPause()
        //handler.removeCallbacks(updateTextRunnable)
    }
}