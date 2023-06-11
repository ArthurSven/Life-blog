package com.devapps.lifeblog.views.User

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devapps.lifeblog.R
import com.devapps.lifeblog.data.remote.firebaseAuth.FirebaseAuth
import com.devapps.lifeblog.data.remote.models.User
import com.devapps.lifeblog.databinding.ActivitySignupuserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UserSignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupuserBinding
    private val firebaseAuth = FirebaseAuth()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupuserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginLink.setOnClickListener {
            val intent = Intent(this, UserLoginActivity::class.java)
            startActivity(intent)
        }
        binding.userRegister.setOnClickListener {
            signupUser()
        }
    }

    private fun signupUser() {
        val firstname = binding.firstname.text.toString()
        val lastname = binding.lastname.text.toString()
        val email = binding.email.text.toString()
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()
        val confirmPassword = binding.confirmpassword.text.toString()

        if(email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() && confirmPassword
        == password) {

            firebaseAuth.registerUser(
                firstname, lastname, email, username, password,
            ) {
                isSuccess, errorMessage ->
                if(isSuccess) {
                    Toast.makeText(this, "User account has been registered!",
                        Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        this,
                        "User account failed to create: $errorMessage",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val auth = firebaseAuth.auth
                    auth.createUserWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main) {
                        checkedState()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@UserSignupActivity, e.message, Toast.LENGTH_LONG)
                            .show()
                    }

                }

                }
        } else {
            Toast.makeText(
                this,
                "Please fill in all the fields and ensure that the passwords match",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun checkedState() {
        val auth = firebaseAuth.auth

        if (auth.currentUser == null) {
            Toast.makeText(this, "You are not logged in", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(this, "You are logged in", Toast.LENGTH_SHORT).show()
        }
    }
}