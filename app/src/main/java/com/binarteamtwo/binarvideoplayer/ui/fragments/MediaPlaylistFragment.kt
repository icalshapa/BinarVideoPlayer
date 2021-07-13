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
import com.binarteamtwo.binarvideoplayer.data.Constant
import com.binarteamtwo.binarvideoplayer.databinding.FragmentMediaPlaylistBinding
import com.binarteamtwo.binarvideoplayer.local.room.MediaPlaylistRoomDatabase
import com.binarteamtwo.binarvideoplayer.local.room.datasource.MediaPlaylistDataSource
import com.binarteamtwo.binarvideoplayer.ui.fragments.adapter.MediaPlaylistAdapter
import com.irfan.binarvideoplayer.model.MediaPlaylist


class MediaPlaylistFragment : Fragment(), MediaPlaylistContract.View {

    private var isFilteredByTaskStatus: Boolean = false
    private lateinit var binding: FragmentMediaPlaylistBinding
    private lateinit var adapter: MediaPlaylistAdapter
    private lateinit var presenter: MediaPlaylistContract.Presenter


    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_FAVORITED_VIDEO = "ARG_FAVORITED_VIDEO"



        @JvmStatic
        fun newInstance(isFilterFavorited: Boolean) =
            MediaPlaylistFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_FAVORITED_VIDEO, isFilterFavorited)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isFilteredByTaskStatus = it.getBoolean(ARG_FAVORITED_VIDEO)
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
        getData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun getData() {
        presenter.getMediaPlaylistByCompleteness(isFilteredByTaskStatus)
    }

    override fun onDataSuccess(playlist: List<MediaPlaylist>) {
        playlist.let {
            adapter.items =  it
        }
    }

    override fun onDataFailed(msg: String?) {
        Toast.makeText(context, msg ?: "Get Data Failed", Toast.LENGTH_SHORT).show()
    }

    override fun onDataEmpty() {
        adapter.items = mutableListOf()

    }

    override fun onDeleteDataSuccess() {
        getData()
    }

    override fun onDeleteDataFailed() {
        Toast.makeText(context, "Delete Data Failed", Toast.LENGTH_SHORT).show()
    }

    override fun setLoadingStatus(isLoading: Boolean) {
        binding.progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    override fun setEmptyStateVisibility(isDataEmpty: Boolean) {
        binding.tvMessage.text = "No Data"
        binding.tvMessage.visibility = if(isDataEmpty) View.VISIBLE else View.GONE
    }

    override fun initList() {
        adapter = MediaPlaylistAdapter({ playlist, pos ->
            //todo : waiting for DetailMediaPlaylistActivity
            //val intent = Intent(context, DetailMediaPlaylistActivity::class.java)
            //intent.putExtra(Constant.EXTRAS_DATA_TODO, playlist.id)
            //startActivity(intent)
        }, { todo, pos ->
            showDialogDeleteTodo(todo)
        })
        binding.rvTask.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MediaPlaylistFragment.adapter
        }


    }

    override fun initView() {
        context?.let {
            val dataSource = MediaPlaylistDataSource(MediaPlaylistRoomDatabase.getInstance(it).mediaPlaylistDao())
            presenter = MediaPlaylistPresenter(dataSource, this@MediaPlaylistFragment)
        }
        initSwipeRefresh()
        initList()
    }


    private fun initSwipeRefresh() {
        binding.srlTask.setOnRefreshListener {
            binding.srlTask.isRefreshing = false
            getData()

        }
    }

    private fun showDialogDeleteTodo(mediaPlaylist: MediaPlaylist) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle("Do you want to delete \"${mediaPlaylist.title}\" ?")
                setPositiveButton("Yes") { dialog, id ->
                    presenter.deleteMediaPlaylist(mediaPlaylist)
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