package com.example.miaupetshop.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.miaupetshop.R
import com.example.miaupetshop.databinding.FragmentCreateAccountBinding

class CreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentCreateAccountBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appCompatButtonBack.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.appCompatButtonCreate.setOnClickListener{
            Toast.makeText(requireContext(), "criado", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_navigation_notifications_to_navigation_home)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        return binding.root
    }


}