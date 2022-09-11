package com.example.moviesnight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.moviesnight.slider.SlideAdapter
import com.example.moviesnight.slider.SlideItem
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //image slider
        viewPager = findViewById(R.id.imageSlider)
        // setting list of movies to slide
        val items = mutableListOf<SlideItem>()
        items.add(SlideItem(R.drawable.testimage))
        items.add(SlideItem(R.drawable.testimage))
        items.add(SlideItem(R.drawable.testimage))
        items.add(SlideItem(R.drawable.testimage))


        viewPager.adapter = SlideAdapter(items)
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val cpt = CompositePageTransformer()
        cpt.addTransformer(MarginPageTransformer(40))
        cpt.addTransformer { view: View, fl: Float ->
            val r = 1 - abs(fl)
            view.scaleY = 0.85f + r * 0.15f
        }
        viewPager.setPageTransformer(cpt)
        //image slider

    }
}