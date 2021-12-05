package com.example.miaupetshop.ui.person

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.miaupetshop.R
import com.example.miaupetshop.databinding.FragmentFavoriteItemBinding
import com.example.miaupetshop.databinding.FragmentPersonBinding

class PersonFragment : Fragment() {

    private lateinit var binding: FragmentPersonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPersonBinding.inflate(layoutInflater,container, false)
        return binding.root
    }
}