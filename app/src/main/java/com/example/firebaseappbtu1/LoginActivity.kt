package com.example.firebaseappbtu1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText : EditText
    private lateinit var loginPasswordEditText : EditText
    private lateinit var loginButton : Button
    private lateinit var forgotPasswordButton : TextView
    private lateinit var notRegisteredButton : TextView

    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        validationForLogin()
        redirectToForgotPassword()
        redirectToSignUp()
    }

    private fun validationForLogin() {
        loginButton.setOnClickListener{
            val email = emailEditText.text.toString()
            val password = loginPasswordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty() || email.contains(" ") ||
                password.length < 8){
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun redirectToForgotPassword() {
        forgotPasswordButton.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun redirectToSignUp() {
        notRegisteredButton.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun init() {
        emailEditText = findViewById(R.id.loginEmailEditText)
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText)
        loginButton = findViewById(R.id.loginButton)
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton)
        notRegisteredButton = findViewById(R.id.notRegisteredButton)
    }
}