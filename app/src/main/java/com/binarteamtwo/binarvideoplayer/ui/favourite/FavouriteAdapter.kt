package com.binarteamtwo.binarvideoplayer.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.data.constant.Constant
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.Movie
import com.binarteamtwo.binarvideoplayer.databinding.ItemMovieBinding
import com.bumptech.glide.Glide

class FavouriteAdapter(
    private val itemClick: (Movie) -> Unit
    ) :
    RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

        var items: List<Movie> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


        class FavouriteViewHolder(
            private val binding: ItemMovieBinding,
            val itemClick: (Movie) -> Unit,
        ) :
            RecyclerView.ViewHolder(binding.root) {


            fun bindView(item: Movie, position: Int)  {
                with(item) {
                    binding.apply {
                        Glide.with(itemView.context)
                            .load(Constant.IMAGE_URL_PATH_POSTER +posterPath)
                            .centerCrop()
                            .placeholder(R.drawable.ic_placeholder)
                            .into(binding.ivMoviePoster)
                        tvMovieTitle.text = title
                        rbMovie.apply {
                            max = 10
                            numStars = 5
                            setIsIndicator(true)
                            rating = (voteAverage?.toFloat() ?:0f)/2

                        }

                    }
                    itemView.setOnClickListener { itemClick(this) }
                    itemView.setOnLongClickListener {
                        true
                    }
                }
            }
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
            val binding =
                ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FavouriteViewHolder(binding, itemClick)
        }

        override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
            holder.bindView(items[position], position)
        }

        override fun getItemCount(): Int = items.size
    }