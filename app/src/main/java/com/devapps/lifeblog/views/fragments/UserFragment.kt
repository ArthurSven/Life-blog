package com.devapps.lifeblog.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.devapps.lifeblog.R
import com.devapps.lifeblog.views.User.UserLoginActivity
import com.devapps.lifeblog.views.User.UserSignupActivity

class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signup = view.findViewById<Button>(R.id.userSignup)

        val login = view.findViewById<Button>(R.id.userLogin)

        signup.setOnClickListener {
            val intent = Intent(this@UserFragment.requireContext(), UserSignupActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            val intent = Intent(this@UserFragment.requireContext(), UserLoginActivity::class.java)
            startActivity(intent)
        }
    }


}