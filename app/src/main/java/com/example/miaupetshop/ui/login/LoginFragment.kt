package com.example.miaupetshop.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.miaupetshop.R
import com.example.miaupetshop.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding.appCompatButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_navigation_home_to_navigation_dashboard)
        }
        binding.appCompatButton2.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_navigation_home_to_navigation_notifications)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

}