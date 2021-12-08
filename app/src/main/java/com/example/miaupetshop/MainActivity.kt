package com.example.miaupetshop

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.miaupetshop.databinding.ActivityMainBinding
import com.example.miaupetshop.ui.home.HomeFragment
import com.example.miaupetshop.ui.login.LoginActivity
import com.example.miaupetshop.ui.notifications.FavoriteFragment
import com.example.miaupetshop.ui.notifications.NotificationsFragment
import com.example.miaupetshop.ui.person.PersonFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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
                R.id.navigation_Bag ->
                    supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_main, NotificationsFragment()).commit()
               R.id.navigation_person ->
                    supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_main, PersonFragment()).commit()
            }

            return@OnNavigationItemSelectedListener true
        })
    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Deseja sair do aplicativo?")
            .setPositiveButton("sim") { _, _ ->
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }.setNegativeButton("Não") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}