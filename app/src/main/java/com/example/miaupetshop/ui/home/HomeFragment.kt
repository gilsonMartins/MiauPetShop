package com.example.miaupetshop.ui.home

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miaupetshop.R
import com.example.miaupetshop.databinding.FragmentHomeBinding
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var slideShow: SliderView
    private var images = intArrayOf(
        R.drawable.exemplo,
        R.drawable.exmplo2,
        R.drawable.exmeplo3,
        R.drawable.servico_1,
        R.drawable.servico_2,
        R.drawable.servico_3,
        R.drawable.banner
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        imgensAdapter()
        productsAdapter()
        productPromoAdapter()
        productFeatured()
    }

    private fun productFeatured() {
        val list =
            mutableListOf(
                ProductsModel("R$50", "Cama Pet 35cm - Marrom", R.drawable.item_1),
                ProductsModel("R$70", "Pote para ração", R.drawable.item_2),
                ProductsModel("R$170", "Colar 5cm", R.drawable.item_3),
                ProductsModel("R$170", "Caminha 38cm - Marrom", R.drawable.item_produto1),
                ProductsModel("R$100", "Caminha 38cm - Azul", R.drawable.item_produto2),
                ProductsModel("R$35,90", "Caminha 38cm - Rosa", R.drawable.item_produto3),
                ProductsModel("R$170", "Caminha 38cm - Café", R.drawable.item_produto4))
        val adapterList = ProductAdapter(list)
        binding.recyclerView2.apply {
            layoutManager =LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = adapterList

        }
        adapterList.itemClickListener(object : ItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(requireContext(), "Deu certo", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun productPromoAdapter() {
        val list =
            mutableListOf(
                ProductPromoModel(R.drawable.servico_1),
                ProductPromoModel( R.drawable.servico_2),
                ProductPromoModel( R.drawable.servico_3))
        val adapterList = ProductsPromoAdapter(list)
        binding.recyclerView3.apply {
            layoutManager =LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = adapterList

        }
        adapterList.itemClickListener(object : ItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(requireContext(), "Deu certo", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun productsAdapter() {
        val list =
            mutableListOf(
                ProductsModel("R$50", "Latinha", R.drawable.item_1),
                ProductsModel("R$70", "Latinha", R.drawable.item_2),
                ProductsModel("R$70", "Latinha", R.drawable.item_3))
        val adapterList = ProductAdapter(list)
        binding.recyclerView.apply {
            layoutManager =LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = adapterList

        }
        adapterList.itemClickListener(object : ItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(requireContext(), "Deu certo", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun imgensAdapter() {
        slideShow = binding.slideShow

        val adapter = SlideAdapter(images)

        slideShow.setSliderAdapter(adapter)
        slideShow.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION)
        slideShow.startAutoCycle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}