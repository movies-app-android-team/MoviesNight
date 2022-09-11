package com.example.moviesnight


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.moviesnight.fragment.AppNavigator
import com.example.moviesnight.fragment.DetailFragment
import com.example.moviesnight.fragment.HomeFragment
import com.example.moviesnight.fragment.SearchFragment

class MainActivity : AppCompatActivity(),AppNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bubbleTB = findViewById<io.ak1.BubbleTabBar>(R.id.bubbleTabBar)
        bubbleTB.addBubbleListener { id ->
            Log.d("myApp", "button $id clicked")
            when (id) {
                R.id.navHome -> navigateToHome()
                R.id.navSearch -> navigateToSearch()
                R.id.navFav -> navigateToDetail()
            }
        }
    }
    override fun navigateToDetail() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder,DetailFragment()).commit()
    }

    override fun navigateToSearch() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder,SearchFragment()).commit()
    }

    override fun navigateToHome() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder,HomeFragment()).commit()
    }
}