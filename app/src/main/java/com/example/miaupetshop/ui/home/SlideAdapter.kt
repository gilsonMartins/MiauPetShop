package com.example.miaupetshop.ui.home

import com.smarteist.autoimageslider.SliderViewAdapter

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import com.example.miaupetshop.R


class SlideAdapter(images: IntArray): SliderViewAdapter<SlideAdapter.Holder>() {

    var images: IntArray = images


    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.slide_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(viewHolder: Holder, position: Int) {
        viewHolder.imageView.setImageResource(images[position])
    }

    override fun getCount(): Int {
        return images.size
    }

    class Holder(itemView: View) : ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.img)

    }
}