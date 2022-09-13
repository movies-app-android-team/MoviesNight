package com.example.moviesnight


import android.os.Bundle
import android.util.Pair as UtilPair
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.moviesnight.fragment.HomeFragmentDirections
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private var counterBackBTN = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bubbleTB = findViewById<io.ak1.BubbleTabBar>(R.id.bubbleTabBar)
        bubbleTB.addBubbleListener { id ->
            Log.d("myApp", "button $id clicked")
            when (id) {
                R.id.navHome -> navigateToHome()
                R.id.navSearch -> navigateToSearch()
                R.id.navFav -> navigateToSavedMovies()
            }
        }
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (counterBackBTN)
                        exitProcess(1)
                    navigateToHome()
                    bubbleTB.setSelected(0, true)
                    counterBackBTN = true
                }
            }
        this.onBackPressedDispatcher.addCallback(this, callback)
    }


    private fun navigateToSearch() {
        if (currentFocus == findViewById(R.id.searchFragment))
            Log.d("myApp", "searchFragment")
        counterBackBTN = false
        findNavController(R.id.nav_host_frag).navigate(R.id.homeToSearch)
    }

    private fun navigateToHome() {
        counterBackBTN = true
//        findNavController(R.id.nav_host_frag)
//            .navigate(R.id.homeFragment)
    }

    private fun navigateToSavedMovies() {
        counterBackBTN = false
//        findNavController(R.id.nav_host_frag).navigate(R.id.homeToSearch)
    }
}