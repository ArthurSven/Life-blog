package com.devapps.lifeblog.views.User

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devapps.lifeblog.R
import com.devapps.lifeblog.data.remote.firebaseAuth.FirebaseAuth
import com.devapps.lifeblog.databinding.ActivityResetPasswordBinding

class PasswordResetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private val firebaseAuth = FirebaseAuth()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.resetButton.setOnClickListener {

                val email = binding.resetEmail.text.toString()
                firebaseAuth.resetPassword(email) { success, error ->
                    if (success) {
                        // Password reset email sent successfully
                        Toast.makeText(this@PasswordResetActivity, "Password reset email sent", Toast.LENGTH_SHORT).show()
                    } else {
                        // Password reset failed
                        Toast.makeText(this@PasswordResetActivity, "Password reset failed: $error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}