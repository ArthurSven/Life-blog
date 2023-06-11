package com.devapps.lifeblog.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.ui.setupWithNavController
import com.devapps.lifeblog.R
import com.devapps.lifeblog.data.local.db.ArticleDatabase
import com.devapps.lifeblog.repository.NewsRepository
import com.devapps.lifeblog.views.fragments.HomeFragment
import com.devapps.lifeblog.views.fragments.NewsFragment
import com.devapps.lifeblog.views.fragments.UserFragment
import com.devapps.lifeblog.views.viewModels.NewsViewModel
import com.devapps.lifeblog.views.viewModels.NewsViewModelProviderFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarMenuView

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Splasher)
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_LifeBlog)

        setContentView(R.layout.activity_main)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(NewsViewModel::class.java)

        val homeFragment = HomeFragment()
        val newsFragment = NewsFragment()
        val userFragment = UserFragment()

        setCurrentFragment(homeFragment)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNav)

        bottomNavigationView.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.home -> homeFragment
                R.id.news -> newsFragment
                R.id.user -> userFragment
                else -> null
            }
            fragment?.let { setCurrentFragment(it) }
            true
        }

    }

    //Function to display current fragment
    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flayout, fragment)
            commit()
        }
    }
}