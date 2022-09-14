package com.example.moviesnight


import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import io.ak1.BubbleTabBar
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    //    private lateinit var homeFragment: Fragment
//    private lateinit var detailFragment: Fragment
//    private lateinit var savedMoviesFragment: Fragment
//    private lateinit var searchFragment: Fragment
//    private var counterBackBTN = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        homeFragment = supportFragmentManager.findFragmentById(R.id.homeFragment)!!
//        detailFragment = supportFragmentManager.findFragmentById(R.id.detailFragment)!!
//        savedMoviesFragment = supportFragmentManager.findFragmentById(R.id.savedMoviesFragment)!!
//        searchFragment = supportFragmentManager.findFragmentById(R.id.searchFragment)!!
        val  navController=findNavController(R.id.nav_host_frag)
        val bubbleTB = findViewById<io.ak1.BubbleTabBar>(R.id.bubbleTabBar)
        bubbleTB.addBubbleListener { id ->
            bubbleTB.onNavDestinationSelected(id, navController)
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bubbleTB.setSelectedWithId(destination.id, false)
        }
//        bubbleTB.addBubbleListener { id ->
//            Log.d("myApp", "button $id clicked")
//            when (id) {
//                R.id.navHome -> navigateToHome()
//                R.id.navSearch -> navigateToSearch()
//                R.id.navFav -> navigateToSavedMovies()
//            }
//        }
//        val callback: OnBackPressedCallback =
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    if (counterBackBTN)
//                        exitProcess(1)
//                    navigateToHome()
//                    bubbleTB.setSelected(0, true)
//                    counterBackBTN = true
//                }
//            }
//        this.onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun navigateToSearch() {
        val toSearch = findNavController(R.id.nav_host_frag)
//        counterBackBTN = false
//        when (currentFocus) {
//            homeFragment -> toSearch.navigate(R.id.homeToSearch)
//            detailFragment -> toSearch.navigate(R.id.detail_to_search)
//            savedMoviesFragment -> toSearch.navigate(R.id.saved_to_search)
//        }
    }
}

private fun BubbleTabBar.onNavDestinationSelected(
    itemId: Int,
    navController: NavController
): Boolean {
    val builder = NavOptions.Builder()
        .setLaunchSingleTop(true)
    if (navController.currentDestination!!.parent!!.findNode(itemId) is ActivityNavigator.Destination) {
        builder.setEnterAnim(R.anim.from_right)
            .setExitAnim(R.anim.to_right)
            .setPopEnterAnim(R.anim.from_right)
            .setPopExitAnim(R.anim.to_left)
    } else {
        builder.setEnterAnim(R.anim.from_right)
            .setExitAnim(R.anim.to_right)
            .setPopEnterAnim(R.anim.from_right)
            .setPopExitAnim(R.anim.from_right)
    }
    //if (itemId == getChildAt(0).id) {
    //builder.setPopUpTo(findStartDestination(navController.graph)!!.id, true)
    // }
    builder.setPopUpTo(itemId, true)
    val options = builder.build()
    return try {
        //TODO provide proper API instead of using Exceptions as Control-Flow.
        navController.navigate(itemId, null, options)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}