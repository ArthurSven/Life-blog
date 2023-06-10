package com.devapps.lifeblog.views.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import com.devapps.lifeblog.R
import com.devapps.lifeblog.data.remote.firebaseAuth.FirebaseAuth
import com.devapps.lifeblog.databinding.ActivityUserLoginBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.math.log

class UserLoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityUserLoginBinding
    private val firebaseAuth = FirebaseAuth()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userLogin.setOnClickListener {
            try {
                loginUser()
            } catch (e: Exception) {
                Toast.makeText(this@UserLoginActivity, e.message, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    fun loginUser() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth.loginUser(email, password) { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(
                        this@UserLoginActivity, "Login successful",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@UserLoginActivity, "Login failed",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        } else {
            Toast.makeText(this@UserLoginActivity, "Ensure that all fields are not empty",
            Toast.LENGTH_LONG).show()
        }
    }
}