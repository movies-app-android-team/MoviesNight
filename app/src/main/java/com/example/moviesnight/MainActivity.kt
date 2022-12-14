package com.example.moviesnight


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.moviesnight.model.Movie
import io.ak1.BubbleTabBar
import io.paperdb.Paper

var bookmarkedMovies = mutableListOf<Movie>()

class MainActivity : AppCompatActivity() {
    private lateinit var bubbleTB: BubbleTabBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)
        val getSavedBookmarks = Paper.book().read<MutableList<Movie>>("bookmarkedMovies")
        if (getSavedBookmarks != null) bookmarkedMovies = getSavedBookmarks
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

    override fun onStop() {
        Paper.book().write("bookmarkedMovies", bookmarkedMovies)
        super.onStop()
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
fun containsMovie(y:MutableList<Movie>, x: Int): Pair<Boolean, Movie?> {
    for(i in y) { if (i.movieID==x) return Pair(true, i) }
    return Pair(false, null)
}

fun containsIndex(x: List<Movie>, y:Int): Int {
    for (i in x.indices) if(y==x[i].movieID) return i
    return -1
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
                || (fromBookmarks && toSearch) -> moveBackward(builder)
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
