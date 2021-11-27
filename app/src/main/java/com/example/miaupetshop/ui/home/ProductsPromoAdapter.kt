package com.example.miaupetshop.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.miaupetshop.R

class ProductsPromoAdapter(private var products:MutableList<ProductPromoModel>):
    RecyclerView.Adapter<ProductsPromoAdapter.Holder>()
{


    private var itemClickListener: ItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_services, parent, false)
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
        fun bindView(model: ProductPromoModel) {
            val imageView: ImageView = itemView.findViewById(R.id.imgProducts)
            itemView.setOnClickListener(this)

            imageView.setImageDrawable(ContextCompat.getDrawable(itemView.context, model.imagemView))
        }


        override fun onClick(v: View?) {
            itemClickListener?.onItemClick(v!!, layoutPosition)
        }

    }

}