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
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.ak1.BubbleTabBar
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    //    private lateinit var homeFragment: Fragment
//    private lateinit var detailFragment: Fragment
//    private lateinit var savedMoviesFragment: Fragment
//    private lateinit var searchFragment: Fragment
//    private var counterBackBTN = true
    override fun onCreate(savedInstanceState: Bundle?) {
        val bottomNavigationView: BottomNavigationView
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        homeFragment = supportFragmentManager.findFragmentById(R.id.homeFragment)!!
//        detailFragment = supportFragmentManager.findFragmentById(R.id.detailFragment)!!
//        savedMoviesFragment = supportFragmentManager.findFragmentById(R.id.savedMoviesFragment)!!
//        searchFragment = supportFragmentManager.findFragmentById(R.id.searchFragment)!!
        val navController = findNavController(R.id.nav_host_frag)
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
    toId: Int,
    navController: NavController
): Boolean {
    val builder = NavOptions.Builder()
        .setLaunchSingleTop(true)
    val toHome = R.id.homeFragment == toId
    val toSearch = R.id.searchFragment == toId
    val toBookmarks = R.id.savedMoviesFragment == toId

    val fromHome = R.id.homeFragment == navController.currentDestination?.id
    val fromSearch = R.id.searchFragment == navController.currentDestination?.id
    val fromBookmarks = R.id.savedMoviesFragment == navController.currentDestination?.id

//    if (navController.currentDestination!!.parent!!.findNode(toId) is ActivityNavigator.Destination) {
//        builder.setEnterAnim(R.anim.from_right)
//            .setExitAnim(R.anim.to_right)
//            .setPopEnterAnim(R.anim.from_right)
//            .setPopExitAnim(R.anim.to_left)

    //  } else {
    Log.d("navi", "else block")
//        builder.setEnterAnim(R.anim.from_right)
//            .setExitAnim(R.anim.to_right)
//            .setPopEnterAnim(R.anim.from_right)
//            .setPopExitAnim(R.anim.from_right)
//    }
    when {
        fromHome && toHome -> {
            builder.setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
        }
        fromHome && toSearch -> {
            builder.setEnterAnim(R.anim.from_right)
                .setExitAnim(R.anim.to_left)
                .setPopEnterAnim(R.anim.from_left)
                .setPopExitAnim(R.anim.to_right)
        }
        fromHome && toBookmarks -> {
            builder.setEnterAnim(R.anim.from_right)
                .setExitAnim(R.anim.to_left)
                .setPopEnterAnim(R.anim.from_left)
                .setPopExitAnim(R.anim.to_right)
        }
        fromSearch && toHome -> {
            builder.setEnterAnim(R.anim.from_left)
                .setExitAnim(R.anim.to_right)
                .setPopEnterAnim(R.anim.from_right)
                .setPopExitAnim(R.anim.to_left)
        }
        fromSearch && toSearch -> {
            builder.setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
        }
        fromSearch && toBookmarks -> {
            builder.setEnterAnim(R.anim.from_right)
                .setExitAnim(R.anim.to_left)
                .setPopEnterAnim(R.anim.from_left)
                .setPopExitAnim(R.anim.to_right)
        }
        fromBookmarks && toHome -> {
            builder.setEnterAnim(R.anim.from_left)
                .setExitAnim(R.anim.to_right)
                .setPopEnterAnim(R.anim.from_right)
                .setPopExitAnim(R.anim.to_left)
        }
        fromBookmarks && toSearch -> {
            builder.setEnterAnim(R.anim.from_left)
                .setExitAnim(R.anim.to_right)
                .setPopEnterAnim(R.anim.from_right)
                .setPopExitAnim(R.anim.to_left)
        }
        fromBookmarks && toBookmarks -> {
            builder.setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
        }
    }

    //if (toId == getChildAt(0).id) {
    //builder.setPopUpTo(findStartDestination(navController.graph)!!.id, true)
    // }
    builder.setPopUpTo(toId, true)
    val options: NavOptions = builder.build()
    return try {
        //TODO provide proper API instead of using Exceptions as Control-Flow.
        navController.navigate(toId, null, options)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}