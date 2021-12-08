package com.example.miaupetshop.ui.account

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miaupetshop.R
import com.example.miaupetshop.databinding.ActivityCreateAccountBinding
import com.example.miaupetshop.ui.login.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPrefLogin = this.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val email = sharedPrefLogin.getString("email", "")
        binding.appCompatButtonCreate.setOnClickListener { view ->
            retorno()
            if (!binding.name.text.isNullOrEmpty() &&
                !binding.phone.text.isNullOrEmpty() &&
                !binding.email.text.isNullOrEmpty() &&
                !binding.password.text.isNullOrEmpty()
            ) {
                if (email!!.contains("")){
                    savePreferences("nome", binding.name.text.toString())
                    savePreferences("email", binding.email.text.toString())
                    savePreferences("phone", binding.password.text.toString())
                    savePreferencesPhone("telefone", binding.phone.text.toString().toInt())
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        binding.appCompatButtonBack.setOnClickListener { view ->
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
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