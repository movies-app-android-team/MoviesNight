package com.example.moviesnight.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.example.moviesnight.bookmarks.BookmarkMovieAdapter
import com.example.moviesnight.bookmarks.BookmarkMovieItem
import com.example.moviesnight.recycler.GenreMovieItem

class SavedMoviesFragment : Fragment() {
    private lateinit var bookmarkRecycler:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_saved_movies, container, false)

        bookmarkRecycler=view.findViewById(R.id.bookmarkRecycler)

        val bookmarkMovies = mutableListOf<BookmarkMovieItem>()
        bookmarkMovies.add(BookmarkMovieItem(1,R.drawable.test2,"Fast and Furious", "Action",4.2f))
        bookmarkMovies.add(BookmarkMovieItem(2,R.drawable.test2,"Tomb Raider", "Action",0f))
        bookmarkMovies.add(BookmarkMovieItem(3,R.drawable.test2,"Maze Runner", "Action",5.5f))
        bookmarkMovies.add(BookmarkMovieItem(4,R.drawable.test2,"Fast and Furious", "Sci-Fi",4.2f))
        bookmarkMovies.add(BookmarkMovieItem(5,R.drawable.test2,"Fast and Furious", "Action",4.2f))
        bookmarkMovies.add(BookmarkMovieItem(1, R.drawable.test2, "Movie 1", "Genre 1", 1.1f))
        bookmarkMovies.add(BookmarkMovieItem(2, R.drawable.test2, "Movie 2", "Genre 2", 2.2f))
        bookmarkMovies.add(BookmarkMovieItem(3, R.drawable.test2, "Movie 3", "Genre 3", 2.2f))
        bookmarkMovies.add(BookmarkMovieItem(4, R.drawable.test2, "Movie 4", "Genre 4", 2.2f))
        bookmarkMovies.add(BookmarkMovieItem(5, R.drawable.test2, "Movie 5", "Genre 5", 2.2f))
        bookmarkMovies.add(BookmarkMovieItem(6, R.drawable.test2, "Movie 6", "Genre 6", 2.2f))
        bookmarkMovies.add(BookmarkMovieItem(7, R.drawable.test2, "Movie 7", "Genre 7", 2.2f))


        bookmarkRecycler.adapter=BookmarkMovieAdapter(bookmarkMovies)

        return view
    }
}