package com.binarteamtwo.binarvideoplayer.ui.fragments.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentViewHolder
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.databinding.FragmentMediaPlaylistBinding
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist
import com.binarteamtwo.binarvideoplayer.databinding.ItemPlaylistBinding
import com.binarteamtwo.binarvideoplayer.ui.fragments.MediaPlaylistFragment
import com.bumptech.glide.Glide
import java.security.AccessController.getContext
import kotlin.coroutines.coroutineContext

class MediaPlaylistAdapter(
    val itemClick: (MediaPlaylist, Int) -> Unit,
    val longClick: (MediaPlaylist, Int) -> Unit
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
                //error 1 tvmedia....
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