package com.binarteamtwo.binarvideoplayer.ui.trailerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.Movie
import com.binarteamtwo.binarvideoplayer.databinding.ItemMovieBinding
import com.binarteamtwo.binarvideoplayer.databinding.ItemTrailerBinding
import com.binarteamtwo.binarvideoplayer.ui.homepage.HomepageAdapter

class TrailerListAdapter (
    private val itemClick: (Movie) -> Unit
): RecyclerView.Adapter<TrailerListAdapter.TrailerListViewHolder>(){

    var items: List<Movie> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    class TrailerListViewHolder (
        private val binding: ItemTrailerBinding,
        val itemClick: (Movie) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: Movie){
            with(item){
                binding.tvTitleTrailer.text = title
                binding.tvTitleMovie.text = title
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrailerListViewHolder {
        val binding = ItemTrailerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TrailerListViewHolder(binding,itemClick)
    }

    override fun onBindViewHolder(holder: TrailerListViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size
}