package com.example.moviesnight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.moviesnight.slider.SlideItem
import com.makeramen.roundedimageview.RoundedImageView

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager : ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.imageSlider)
        val items = mutableListOf<SlideItem>()
        items.add(SlideItem(R.drawable.testimage))
    }
}