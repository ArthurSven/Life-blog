package com.devapps.lifeblog.views.User.UserFragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devapps.lifeblog.R
import com.devapps.lifeblog.views.User.UserLoginActivity

class UserHomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = requireContext().getSharedPreferences("SessionPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (!isLoggedIn) {
            // User is not logged in, redirect to login activity
            val intent = Intent(requireContext(), UserLoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
            return
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_home, container, false)
    }

}