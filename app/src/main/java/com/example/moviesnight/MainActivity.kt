package com.example.moviesnight


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.moviesnight.model.Movie
import io.ak1.BubbleTabBar

var bookmarkedMovies = mutableListOf<Movie>()
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
    }
}

private fun moveBackward(x: NavOptions.Builder) {
    x.setEnterAnim(R.anim.from_left)
        .setExitAnim(R.anim.to_right)
        .setPopEnterAnim(R.anim.from_right)
        .setPopExitAnim(R.anim.to_left)
}
private fun moveForward(x: NavOptions.Builder) {
    x.setEnterAnim(R.anim.from_right)
        .setExitAnim(R.anim.to_left)
        .setPopEnterAnim(R.anim.from_left)
        .setPopExitAnim(R.anim.to_right)
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
        (fromHome && toSearch)
                || (fromHome && toBookmarks)
                || (fromSearch && toBookmarks) -> moveForward(builder)
        (fromSearch && toHome)
                || (fromBookmarks && toHome)
                || (fromBookmarks && toSearch)-> moveBackward(builder)
    }
    builder.setPopUpTo(toId, true)
    val options: NavOptions = builder.build()
    return try {
        navController.navigate(toId, null, options)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}
