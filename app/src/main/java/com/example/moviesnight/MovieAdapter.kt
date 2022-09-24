//package com.example.moviesnight
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.moviesnight.models.Moviee
////import kotlinx.android.synthetic.main.bookmark_movie_item_layout.view.*
//
//
//
//class MovieAdapter(private val movies : List<Moviee>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
//
//    class MovieViewHolder(view: View) :RecyclerView.ViewHolder(view){
//
//        fun bindMovie(movie : Moviee){
//        itemView.book
//
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
//        return MovieViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.bookmark_movie_item_layout, parent, false)
//        )
//    }
//
//    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//       holder.bindMovie(movies[position])
//    }
//
//    override fun getItemCount(): Int {
//       return movies.size
//    }
//}