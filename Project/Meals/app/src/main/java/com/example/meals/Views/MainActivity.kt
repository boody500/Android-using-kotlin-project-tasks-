package com.example.meals.Views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.meals.R
import com.example.meals.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val auth = Firebase.auth
        val user = auth.currentUser
        val userID:String
        Log.i("TAG", "onCreate:${user?.uid}")

        if (user?.uid == null)
            userID = "guest"

        else
            userID = user.uid
        replace_fragment(Home_Fragment(this,userID))

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.Home -> replace_fragment(Home_Fragment(this,userID))
                R.id.Favorite -> replace_fragment(Favorite_Fragment(this,userID))
                R.id.Search -> replace_fragment(Search_Fragment(this,userID))
                R.id.Account -> replace_fragment(BlankFragment(this))


                else ->{}
            }
            true
        }
    }

    private fun replace_fragment(current_fragment: Fragment){
        val fragment_manager = supportFragmentManager
        val fragment_transaction = fragment_manager.beginTransaction()
        fragment_transaction.replace(R.id.Frame_Layout,current_fragment)
        fragment_transaction.addToBackStack(null)
        fragment_transaction.commit()
    }


}