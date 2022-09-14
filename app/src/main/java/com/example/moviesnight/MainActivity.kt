package com.example.moviesnight


import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.*
import io.ak1.BubbleTabBar
import kotlin.system.exitProcess

private var counterBackButton = true

class MainActivity : AppCompatActivity() {
    private lateinit var bubbleTB: BubbleTabBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_frag)
        bubbleTB = findViewById(R.id.bubbleTabBar)
        bubbleTB.addBubbleListener { id ->
            onNavDestinationSelected(id, navController)
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bubbleTB.setSelectedWithId(destination.id, false)
        }
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (counterBackButton) {
                        exitProcess(1)
                    }
                    counterBackButton = true
                    val x = findNavController(R.id.nav_host_frag)
                    supportFragmentManager.findFragmentById(R.id.homeFragment)
                        ?.let { onNavDestinationSelected(it.id, x) }
                    bubbleTB.setSelected(0, true)
                }
            }
        this.onBackPressedDispatcher.addCallback(this, callback)
    }
}

private fun onNavDestinationSelected(
    toId: Int,
    navController: NavController
): Boolean {
    val builder = NavOptions.Builder()
        .setLaunchSingleTop(true)
    val toHome = R.id.homeFragment == toId
    val toSearch = R.id.searchFragment == toId
    val toBookmarks = R.id.bookmarksFragment == toId
    val fromHome = R.id.homeFragment == navController.currentDestination?.id
    val fromSearch = R.id.searchFragment == navController.currentDestination?.id
    val fromBookmarks = R.id.bookmarksFragment == navController.currentDestination?.id
    when {
        fromHome && toSearch -> {
            counterBackButton = false
            builder.setEnterAnim(R.anim.from_right)
                .setExitAnim(R.anim.to_left)
                .setPopEnterAnim(R.anim.from_left)
                .setPopExitAnim(R.anim.to_right)
        }
        fromHome && toBookmarks -> {
            counterBackButton = false
            builder.setEnterAnim(R.anim.from_right)
                .setExitAnim(R.anim.to_left)
                .setPopEnterAnim(R.anim.from_left)
                .setPopExitAnim(R.anim.to_right)
        }
        fromSearch && toHome -> {
            counterBackButton = true
            builder.setEnterAnim(R.anim.from_left)
                .setExitAnim(R.anim.to_right)
                .setPopEnterAnim(R.anim.from_right)
                .setPopExitAnim(R.anim.to_left)
        }
        fromSearch && toBookmarks -> {
            counterBackButton = false
            builder.setEnterAnim(R.anim.from_right)
                .setExitAnim(R.anim.to_left)
                .setPopEnterAnim(R.anim.from_left)
                .setPopExitAnim(R.anim.to_right)
        }
        fromBookmarks && toHome -> {
            counterBackButton = true
            builder.setEnterAnim(R.anim.from_left)
                .setExitAnim(R.anim.to_right)
                .setPopEnterAnim(R.anim.from_right)
                .setPopExitAnim(R.anim.to_left)
        }
        fromBookmarks && toSearch -> {
            counterBackButton = false
            builder.setEnterAnim(R.anim.from_left)
                .setExitAnim(R.anim.to_right)
                .setPopEnterAnim(R.anim.from_right)
                .setPopExitAnim(R.anim.to_left)
        }
    }
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