package com.example.miaupetshop

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.miaupetshop.databinding.ActivityMainBinding
import com.example.miaupetshop.ui.dashboard.DashboardFragment
import com.example.miaupetshop.ui.home.HomeFragment
import com.example.miaupetshop.ui.notifications.FavoriteFragment
import com.example.miaupetshop.ui.notifications.NotificationsFragment
import com.example.miaupetshop.ui.person.PersonFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        NavController(this)
        bottomNavigationView = binding.navView
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_main,HomeFragment()).commit()
                }
                R.id.navigation_dashboard -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_main, FavoriteFragment()).commit()
                }
                R.id.navigation_whatsapp ->
                    supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_main, NotificationsFragment()).commit()
               R.id.navigation_person ->
                    supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_main, PersonFragment()).commit()
            }

            return@OnNavigationItemSelectedListener true
        })
    }
}