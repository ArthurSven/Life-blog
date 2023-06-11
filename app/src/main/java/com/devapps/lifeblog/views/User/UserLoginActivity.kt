package com.devapps.lifeblog.views.User

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import com.devapps.lifeblog.R
import com.devapps.lifeblog.data.remote.firebaseAuth.FirebaseAuth
import com.devapps.lifeblog.databinding.ActivityUserLoginBinding


class UserLoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityUserLoginBinding
    private val firebaseAuth = FirebaseAuth()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerLink.setOnClickListener {
            val intent = Intent(this, UserSignupActivity::class.java)
            startActivity(intent)
        }

        binding.resetLink.setOnClickListener {
            val intent = Intent(this, PasswordResetActivity::class.java)
            startActivity(intent)
        }

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
                    startSession()
                        val intent = Intent(this, UserHomeActivity::class.java)
                        startActivity(intent)
                        finish()
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

    private fun startSession() {
        val sharedPreferences = getSharedPreferences("SessionPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }
}