package com.example.firebaseappbtu1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var emailEditText : EditText
    private lateinit var sendResetLinkButton: Button

    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        init()
        sendResetLink()
    }

    private fun sendResetLink() {
        sendResetLinkButton.setOnClickListener {
            val email = emailEditText.text.toString()
            auth.sendPasswordResetEmail(email).addOnSuccessListener {
                Toast.makeText(this, "Reset Email Sent", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun init() {
        emailEditText = findViewById(R.id.forgotPasswordEmailEditText)
        sendResetLinkButton = findViewById(R.id.sendResetLinkButton)
    }
}