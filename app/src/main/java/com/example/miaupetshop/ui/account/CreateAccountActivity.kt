package com.example.miaupetshop.ui.account

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.miaupetshop.R
import com.example.miaupetshop.databinding.ActivityCreateAccountBinding
import com.example.miaupetshop.ui.login.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var mViewModel: GuestViewModel
    private lateinit var binding: ActivityCreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mViewModel = ViewModelProvider(this).get(GuestViewModel::class.java)
        val sharedPrefLogin = this.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val email = sharedPrefLogin.getString("email", "")
        binding.appCompatButtonCreate.setOnClickListener { view ->
            retorno()
            if (!binding.name.text.isNullOrEmpty() &&
                !binding.phone.text.isNullOrEmpty() &&
                !binding.email.text.isNullOrEmpty() &&
                !binding.password.text.isNullOrEmpty()
            ) {
                mViewModel.save(
                    binding.name.text.toString(),
                    binding.email.text.toString(),
                    binding.password.text.toString(),
                    binding.phone.text.toString().toInt(),
                    view.id
                )
            }
        }
        binding.appCompatButtonBack.setOnClickListener { view ->
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        mViewModel.create.observe(this, Observer {
            if (it.success()) {
                savePreferences("nome", binding.name.text.toString())
                savePreferences("email", binding.email.text.toString())
                savePreferences("phone", binding.password.text.toString())
                savePreferencesPhone("telefone", binding.phone.text.toString().toInt())
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(applicationContext, it.failure(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Deseja cancelar seu cadastro?")
            .setPositiveButton("sim") { _, _ ->
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }.setNegativeButton("Não") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun retorno() {
        validate(binding.textInputLayoutPassword, binding.password)
        validate(binding.textInputLayoutEmail, binding.email)
        validate(binding.textInputLayout, binding.phone)
        validate(binding.textInputLayoutName, binding.name)
    }

    private fun validate(error: TextInputLayout, editText: EditText) =
        if (editText.text.isNullOrEmpty()) {
            editText.requestFocus()
            error.error = "Este campo não pode ser vazio"
        } else {
            error.error = null
        }

    fun savePreferences(preferencesString: String, valor: String) {
        this.getSharedPreferences(
            "Login", Context.MODE_PRIVATE,
        )
            .edit()
            .putString(preferencesString, valor)
            .apply()
    }

    fun savePreferencesPhone(preferencesString: String, int: Int) {
        this.getSharedPreferences(
            "Phone", Context.MODE_PRIVATE,
        )
            .edit()
            .putInt(preferencesString, int)
            .apply()
    }
}