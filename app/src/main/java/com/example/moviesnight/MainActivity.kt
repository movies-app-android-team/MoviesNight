package com.example.moviesnight


import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.moviesnight.models.Moviee
import com.example.moviesnight.models.MovieResponse
import com.example.moviesnight.services.MovieApiServices
import com.example.moviesnight.services.MovieInterfaceApi
import io.ak1.BubbleTabBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var loadingBar : ProgressBar
    private lateinit var bubbleTB: BubbleTabBar
   // private lateinit var mySharedPreferences: SharedPreferences
   // private lateinit var bookmarkButton: ImageView
   // private lateinit var bookmarkRecycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_frag)
        bubbleTB = findViewById(R.id.bubbleTabBar)
//       loadingBar = findViewById(R.id.movieDetailsProgressBar)
       // bookmarkButton = findViewById(R.id.recyclerMoviesBookmarkStatus)
        //bookmarkRecycler = findViewById(R.id.bookmarkRecycler)

        //mySharedPreferences = getSharedPreferences("com_example_moviesnight_USERS_BOOKMARK", Context.MODE_PRIVATE)

        //mySharedPreferences.edit{
           // putInt("Bookmark_Value", 2)
           // commit()
      //  }
       // mySharedPreferences.getInt("Bookmark_Value", 2)

        bubbleTB.addBubbleListener { id ->
            onNavDestinationSelected(id, navController)
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bubbleTB.setSelectedWithId(destination.id, false)
        }
//
//        val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org").addConverterFactory(
//            GsonConverterFactory.create()).build()
//        val moviesServices = retrofit.create(MoviesServices::class.java)
//        moviesServices.getMovies().enqueue(object : Callback<List<Movies>>{
//            override fun onResponse(call: Call<List<Movies>>, response: Response<List<Movies>>) {
//                loadingBar.visibility = View.GONE
//                val movies: List<Movies>? = response.body()
//            }
//
//            override fun onFailure(call: Call<List<Movies>>, t: Throwable) {
//                loadingBar.visibility = View.GONE
//                println(t.message)
//            }
//
//        })



    }

    private fun getMovieData(callback: (List<Moviee>) -> Unit){
        val apiService = MovieApiServices.getInstance().create(MovieInterfaceApi::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
            return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }
        })















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
}}