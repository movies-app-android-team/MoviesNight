package com.example.moviesnight.slider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.makeramen.roundedimageview.RoundedImageView

class SlideAdapter(private val items: List<SlideItem>) :
    RecyclerView.Adapter<SlideAdapter.SlideItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideItemViewHolder {
        return SlideItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_slider, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SlideItemViewHolder, position: Int) {
        holder.bindFruit(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class SlideItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemImage: RoundedImageView
        //private val myIntent: Intent = Intent(itemView.context, TestClass::class.java)

        init {
            itemImage = itemView.findViewById(R.id.slidedImage)
            itemView.setOnClickListener {
                // goo here
            }
        }

        fun bindFruit(anItem: SlideItem) {
            itemImage.setImageResource(anItem.imageModelNumber)
        }
    }
}