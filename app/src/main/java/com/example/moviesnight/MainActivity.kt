package com.example.moviesnight


import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var homeFragment: Fragment
    private lateinit var detailFragment: Fragment
    private lateinit var savedMoviesFragment: Fragment
    private lateinit var searchFragment: Fragment
    private var counterBackBTN = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homeFragment = supportFragmentManager.findFragmentById(R.id.homeFragment)!!
        detailFragment = supportFragmentManager.findFragmentById(R.id.detailFragment)!!
        savedMoviesFragment = supportFragmentManager.findFragmentById(R.id.savedMoviesFragment)!!
        searchFragment = supportFragmentManager.findFragmentById(R.id.searchFragment)!!
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
        val toSearch = findNavController(R.id.nav_host_frag)
        counterBackBTN = false
        when (currentFocus) {
            homeFragment -> toSearch.navigate(R.id.homeToSearch)
            detailFragment -> toSearch.navigate(R.id.detail_to_search)
            savedMoviesFragment -> toSearch.navigate(R.id.saved_to_search)
        }
    }

    private fun navigateToHome() {
        val toSearch = findNavController(R.id.nav_host_frag)
        counterBackBTN = false
        when (currentFocus) {
            savedMoviesFragment -> toSearch.navigate(R.id.saved_to_home)
            detailFragment -> toSearch.navigate(R.id.detail_to_home)
            searchFragment -> toSearch.navigate(R.id.search_to_home)
        }
    }

    private fun navigateToSavedMovies() {
        val toSearch = findNavController(R.id.nav_host_frag)
        counterBackBTN = false
        when (currentFocus) {
            homeFragment -> toSearch.navigate(R.id.home_to_saved_movies)
            detailFragment -> toSearch.navigate(R.id.homeToDetail)
            searchFragment -> toSearch.navigate(R.id.homeToSearch)
        }
    }
}