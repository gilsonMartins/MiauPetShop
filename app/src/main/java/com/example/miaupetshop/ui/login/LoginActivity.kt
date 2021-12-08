package com.example.miaupetshop.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.miaupetshop.MainActivity
import com.example.miaupetshop.databinding.ActivityLoginBinding
import com.example.miaupetshop.ui.account.CreateAccountActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.appCompatButton.setOnClickListener { view ->
            mViewModel.doLogin(binding.login.text.toString(), binding.password.text.toString())
        }
        binding.appCompatButton2.setOnClickListener { view ->
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
            finish()
        }
        mViewModel.login.observe(this, Observer {
            if (it.success()) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(applicationContext, it.failure(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}