package com.binarteamtwo.binarvideoplayer.ui.trailerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.TrailerResult
import com.binarteamtwo.binarvideoplayer.databinding.ItemTrailerBinding

class TrailerListAdapter (
    private val itemClick: (TrailerResult) -> Unit
): RecyclerView.Adapter<TrailerListAdapter.TrailerListViewHolder>(){

    var items: List<TrailerResult> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    class TrailerListViewHolder (
        private val binding: ItemTrailerBinding,
        val itemClick: (TrailerResult) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: TrailerResult){
            with(item){
                binding.tvTitleTrailer.text = title
                binding.tvTitleMovie.text = type
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