package com.example.miaupetshop.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
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
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.appCompatButton.setOnClickListener { view ->
            binding.textInputLayoutEmail.visibility = VISIBLE
            binding.textInputLayoutPassword.visibility = VISIBLE
            binding.Logar.visibility = VISIBLE
            binding.appCompatButton.visibility = GONE
            binding.appCompatButton2.visibility = GONE
            binding.back.visibility = VISIBLE
        }
        binding.back.setOnClickListener{
            binding.textInputLayoutEmail.visibility = GONE
            binding.textInputLayoutPassword.visibility =GONE
            binding.Logar.visibility = GONE
            binding.appCompatButton.visibility = VISIBLE
            binding.appCompatButton2.visibility = VISIBLE
            binding.back.visibility = GONE
        }
        binding.Logar.setOnClickListener {
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