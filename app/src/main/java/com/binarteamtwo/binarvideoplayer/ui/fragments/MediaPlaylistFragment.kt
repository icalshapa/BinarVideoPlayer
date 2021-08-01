package com.binarteamtwo.binarvideoplayer.ui.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.base.GenericViewModelFactory
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.constant.Constant
import com.binarteamtwo.binarvideoplayer.databinding.FragmentMediaPlaylistBinding
import com.binarteamtwo.binarvideoplayer.data.local.room.MediaPlaylistRoomDatabase
import com.binarteamtwo.binarvideoplayer.data.local.room.datasource.MediaPlaylistDataSource
import com.binarteamtwo.binarvideoplayer.ui.fragments.adapter.MediaPlaylistAdapter
import com.binarteamtwo.binarvideoplayer.data.model.MediaPlaylist
import com.binarteamtwo.binarvideoplayer.ui.player.PlayerActivity


class MediaPlaylistFragment : Fragment(), MediaPlaylistContract.View {

    private var isFilteredByFavorite: Boolean = false
    private lateinit var binding: FragmentMediaPlaylistBinding
    private lateinit var adapter: MediaPlaylistAdapter
    private lateinit var viewModel: MediaPlaylistViewModel


    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_FAVORITE_VIDEO = "ARG_FAVORITE_VIDEO"


        @JvmStatic
        fun newInstance(isFilterFavorite: Boolean) =
            MediaPlaylistFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_FAVORITE_VIDEO, isFilterFavorite)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isFilteredByFavorite = it.getBoolean(ARG_FAVORITE_VIDEO)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMediaPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        getData(isFilteredByFavorite)
    }

    override fun getData(isFavorite: Boolean) {
        viewModel.getFavoriteMediaPlaylist(isFavorite)
    }


    override fun onDataSuccess(playlist: List<MediaPlaylist>) {
        playlist.let {
            adapter.items = it
        }
    }

    override fun onDataFailed(msg: String?) {
        Toast.makeText(context, msg ?: getString(R.string.main_toast_failed_fetch), Toast.LENGTH_SHORT).show()
    }

    override fun onDataEmpty() {
        adapter.items = mutableListOf()
    }

    override fun onDeleteDataSuccess() {
        getData(isFilteredByFavorite)
    }

    override fun onDeleteDataFailed() {
        Toast.makeText(context, getString(R.string.main_toast_failed_delete), Toast.LENGTH_SHORT).show()
    }

    override fun setLoadingStatus(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun setEmptyStateVisibility(isDataEmpty: Boolean) {
        binding.tvMessage.text = getString(R.string.main_message_empty)
        binding.tvMessage.visibility = if (isDataEmpty) View.VISIBLE else View.GONE
    }

    override fun initList() {
        adapter = MediaPlaylistAdapter({ playlist, pos ->
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra(Constant.EXTRAS_DATA_VIDEO, playlist.id)
            startActivity(intent)
        }, { mediaplaylist, pos ->
            showDialogDeleteMediaPlaylist(mediaplaylist)
        })
        binding.rvTask.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MediaPlaylistFragment.adapter
        }
    }

    override fun initView() {
        initViewModel()
        initSwipeRefresh()
        initList()
    }

    override fun initViewModel() {
        context?.let {
            val dataSource = MediaPlaylistDataSource(
                MediaPlaylistRoomDatabase.getInstance(it).mediaPlaylistDao()
            )
            val repository = MediaPlaylistRepository(dataSource)
            viewModel = GenericViewModelFactory(MediaPlaylistViewModel(repository)).create(MediaPlaylistViewModel::class.java)
        }
        viewModel.videoData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    setLoadingStatus(true)
                    setEmptyStateVisibility(false)
                }
                is Resource.Success -> {
                    setLoadingStatus(false)
                    it.data?.let { data ->
                        if(data.isNullOrEmpty()){
                            onDataEmpty()
                            setEmptyStateVisibility(true)
                        }else{
                            onDataSuccess(data)
                        }
                    }
                }
                is Resource.Error -> {
                    setLoadingStatus(false)
                    setEmptyStateVisibility(false)
                    onDataFailed(it.message.orEmpty())
                }
            }
        })
        viewModel.deleteResponse.observe(viewLifecycleOwner, { isSuccessDelete ->
            if(isSuccessDelete){
                onDeleteDataSuccess()
            }else{
                onDeleteDataFailed()
            }
        })
    }


    private fun initSwipeRefresh() {
        binding.srlTask.setOnRefreshListener {
            binding.srlTask.isRefreshing = false
            getData(isFilteredByFavorite)
        }
    }

    private fun showDialogDeleteMediaPlaylist(mediaPlaylist: MediaPlaylist) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle("Do you want to delete \"${mediaPlaylist.title}\" ?")
                setPositiveButton("Yes") { dialog, id ->
                    viewModel.deleteMediaPlaylist(mediaPlaylist)
                    dialog?.dismiss()
                }
                setNegativeButton("Cancel") { dialog, id ->
                    // User cancelled the dialog
                    dialog?.dismiss()
                }
            }
            builder.create()
        }
        alertDialog?.show()
    }
}