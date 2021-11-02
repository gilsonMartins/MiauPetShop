package com.example.miaupetshop.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.miaupetshop.R
import com.smarteist.autoimageslider.SliderViewAdapter

class ProductAdapter(private var products: MutableList<ProductsModel>) :
    RecyclerView.Adapter<ProductAdapter.Holder>() {

    private var itemClickListener: ItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_products, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindView(products[position])
    }

    fun itemClickListener(ic: ItemClickListener) {
        this.itemClickListener = ic
    }

    override fun getItemCount() = products.size

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindView(model: ProductsModel) {
            val imageView: ImageView = itemView.findViewById(R.id.imgProducts)
            val nameProduct: TextView = itemView.findViewById(R.id.productName)
            val valueProduct: TextView = itemView.findViewById(R.id.productValue)
            itemView.setOnClickListener(this)

            valueProduct.text = model.value
            imageView.setImageDrawable(ContextCompat.getDrawable(itemView.context, model.imageView))
            nameProduct.text = model.name
        }


        override fun onClick(v: View?) {
            itemClickListener?.onItemClick(v!!, layoutPosition)
        }

    }
}