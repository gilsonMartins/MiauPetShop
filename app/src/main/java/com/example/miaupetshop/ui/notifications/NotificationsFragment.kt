package com.example.miaupetshop.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miaupetshop.R
import com.example.miaupetshop.databinding.FragmentNotificationsBinding
import com.example.miaupetshop.ui.home.ProductAdapter
import com.example.miaupetshop.ui.home.ProductsModel
import com.google.gson.Gson

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var binding: FragmentNotificationsBinding
    private lateinit var produto: ProductsModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)
        if (arguments != null && !arguments?.isEmpty!!) {
            produto =
                Gson().fromJson(
                    arguments?.getString("produtoSelecionado"),
                    ProductsModel::class.java
                )
            binding.produto.text = produto.name
            binding.totalItem.text = produto.value
            binding.valortotal.text = produto.value
            binding.imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(), produto.imageView))

        }
        binding.appCompatButton4.setOnClickListener {
            if (arguments != null && !arguments?.isEmpty!!){
                Toast.makeText(requireContext(), "Finalizado", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
                arguments?.isEmpty
            }else
                Toast.makeText(requireContext(), "Sem produto na sacola", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}