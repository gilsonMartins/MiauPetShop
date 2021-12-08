package com.example.miaupetshop.ui.person

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.miaupetshop.R
import com.example.miaupetshop.databinding.FragmentFavoriteItemBinding
import com.example.miaupetshop.databinding.FragmentPersonBinding
import com.example.miaupetshop.ui.login.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PersonFragment : Fragment() {

    private lateinit var binding: FragmentPersonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPersonBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPrefLogin = requireContext().getSharedPreferences("Login", Context.MODE_PRIVATE)
        val name = sharedPrefLogin.getString("nome", "Nome")
        val email = sharedPrefLogin.getString("email", "Email")

        binding.back.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Deseja sair da sua conta?")
                .setPositiveButton("sim") { _, _ ->
                    requireActivity().finish()
                }.setNegativeButton("Não") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        val sharedPref = requireContext().getSharedPreferences("Phone", Context.MODE_PRIVATE)
        val largura = sharedPref.getInt("telefone", 8000)
        binding.phone.text = largura.toString()
        binding.email.text = email
        binding.name.text = name
    }
}