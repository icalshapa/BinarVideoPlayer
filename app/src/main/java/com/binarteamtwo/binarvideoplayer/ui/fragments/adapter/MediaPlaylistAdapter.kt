package com.binarteamtwo.binarvideoplayer.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist
import com.binarteamtwo.binarvideoplayer.databinding.ItemPlaylistBinding
import com.bumptech.glide.Glide

class MediaPlaylistAdapter(
    private val itemClick: (MediaPlaylist, Int) -> Unit,
    private val longClick: (MediaPlaylist, Int) -> Unit
) :
    RecyclerView.Adapter<MediaPlaylistAdapter.FragmentsViewHolder>() {



    var items: List<MediaPlaylist> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class FragmentsViewHolder(
        private val binding: ItemPlaylistBinding,
        val itemClick: (MediaPlaylist, Int) -> Unit,
        val longClick: (MediaPlaylist, Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindView(item: MediaPlaylist, position: Int) {
            with(item) {
                binding.tvTitleSong.text = item.title
                binding.tvSingerName.text = item.singer

                Glide.with(itemView.context)
                    .load(imgIconUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_placeholder)
                    .into(binding.ivIconSong)
                itemView.setOnClickListener { itemClick(this, position) }
                itemView.setOnLongClickListener {
                    longClick(this, position)
                    true
                }


            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentsViewHolder {
        val binding =
            ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FragmentsViewHolder(binding, itemClick, longClick)
    }

    override fun onBindViewHolder(holder: FragmentsViewHolder, position: Int) {
        holder.bindView(items[position], position)

    }

    override fun getItemCount(): Int = items.size
}