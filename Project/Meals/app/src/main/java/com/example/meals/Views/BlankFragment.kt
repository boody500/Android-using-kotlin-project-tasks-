package com.example.meals.Views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.meals.R

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class BlankFragment(val activity:MainActivity) : Fragment() {

    //for firebase
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(activity,gso)



        val textView = view.findViewById<TextView>(R.id.textView3)

        val auth = Firebase.auth
        val user = auth.currentUser

        if (user != null) {
            val userName = user.displayName
            textView.text = "Welcome, " + userName

        }
        else {
            // Handle the case where the user is not signed in
            textView.text = "Welcome Guest"
            val button = view.findViewById<Button>(R.id.button)
            button.text = "Log in"
        }



        val sign_out_button = view.findViewById<Button>(R.id.button)
        sign_out_button.setOnClickListener {
            signOutAndStartSignInActivity()
        }
    }

    private fun signOutAndStartSignInActivity() {
        mAuth.signOut()

        mGoogleSignInClient.signOut().addOnCompleteListener(activity) {
            // Optional: Update UI or show a message to the user
            val intent = Intent(activity, SignInActivity::class.java)
            startActivity(intent)
            activity.finish()
        }
    }
}