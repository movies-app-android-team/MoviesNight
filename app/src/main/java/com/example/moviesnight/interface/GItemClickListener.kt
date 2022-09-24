
package com.example.moviesnight.`interface`

import android.view.View
import com.example.moviesnight.models.Moviee
import com.example.moviesnight.models.MoviesGenre

interface GItemClickListener {
    fun onGMovieItemClick(view: View, movieItem: MoviesGenre)
}