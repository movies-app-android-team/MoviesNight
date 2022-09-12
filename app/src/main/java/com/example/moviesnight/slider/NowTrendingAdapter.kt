package com.example.moviesnight.slider

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.makeramen.roundedimageview.RoundedImageView

class NowTrendingAdapter(private val items: List<NowTrendingItem>) :
    RecyclerView.Adapter<NowTrendingAdapter.NowTrendingItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowTrendingItemViewHolder {
        return NowTrendingItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.slider_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NowTrendingItemViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class NowTrendingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemImage: RoundedImageView
        //private val myIntent: Intent = Intent(itemView.context, TestClass::class.java)

        init {
            itemImage = itemView.findViewById(R.id.slidedImage)
            itemView.setOnClickListener {
                // code goes here
                Log.d("myApp", "item ${items[layoutPosition]} clicked")
            }
        }

        fun bindItem(anItem: NowTrendingItem) {
            itemImage.setImageResource(anItem.imageModelNumber)
        }
    }
}