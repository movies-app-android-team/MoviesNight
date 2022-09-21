package com.example.moviesnight.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.example.moviesnight.`interface`.MovieClickListener
import com.example.moviesnight.bookmarkedMovies
import com.example.moviesnight.model.Movie
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import io.paperdb.Paper

class BookmarkMovieAdapter(
    private val bookmarkMovies: List<Movie>,
    val bInterface: MovieClickListener
) :
    RecyclerView.Adapter<BookmarkMovieAdapter.BookmarkMovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkMovieViewHolder {
        return BookmarkMovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_movie_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookmarkMovieViewHolder, position: Int) {
        holder.bindItem(bookmarkMovies[position])
    }

    override fun getItemCount(): Int {
        return bookmarkMovies.size
    }


    inner class BookmarkMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookmarkMovieImageView: RoundedImageView
        private val bookmarkStatus: ImageView
//        private val bookmarkMovieName: TextView
//        private val bookmarkMovieGenre: TextView
//        private val bookmarkMovieRating: TextView
        private val imageBase="https://image.tmdb.org/t/p/w500/"
        init {
            bookmarkMovieImageView = itemView.findViewById(R.id.recyclerMovieImage)
//            bookmarkMovieName = itemView.findViewById(R.id.bookmarkMovieName)
//            bookmarkMovieGenre = itemView.findViewById(R.id.bookmarkMovieGenre)
//            bookmarkMovieRating = itemView.findViewById(R.id.bookmarkMovieRating)
            bookmarkStatus = itemView.findViewById(R.id.recyclerMoviesBookmarkStatus)
            itemView.setOnClickListener {
                bInterface.onMovieItemClick(it, bookmarkMovies[layoutPosition])
                Log.d("myApp", "item ${bookmarkMovies[layoutPosition]} clicked")
            }
            bookmarkStatus.setOnClickListener {
                if(/*bookmarkMovies[layoutPosition].isBookmarked*/Paper.book().read<Int>("${bookmarkMovies[layoutPosition].movieID}")==1) {
                    /*bookmarkMovies[layoutPosition].isBookmarked = false*/Paper.book().delete("${bookmarkMovies[layoutPosition].movieID}")
                    bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
                    Paper.book().delete("${bookmarkMovies [layoutPosition].movieID}")
                    bookmarkedMovies.remove(bookmarkMovies[layoutPosition])
                    notifyDataSetChanged()
                    //handle un bookmarking here
                    return@setOnClickListener
                }
//                bookmarkMovies[layoutPosition].isBookmarked = true
//                bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
//                //handle bookmarking here
//                Paper.book().write("${bookmarkMovies[layoutPosition].movieID}",1)
//                bookmarkedMovies.add(bookmarkMovies[layoutPosition])
//                notifyDataSetChanged()
            }
        }

        fun bindItem(anItem: Movie) {
            Picasso.get().load(imageBase+anItem.posterPath).into(bookmarkMovieImageView)
//            bookmarkMovieName.text = anItem.movieTitle
//            bookmarkMovieGenre.text = anItem.genres.toString()
//            bookmarkMovieRating.text = "${anItem.voteAverage}"
            if(/*anItem.isBookmarked*/Paper.book().read<Int>("${anItem.movieID}")==1) {
                bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                return
            }
            Log.d("Paper","Didn't pass here")
//            bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
        }
    }
}