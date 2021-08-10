package com.binarteamtwo.binarvideoplayer.ui.homepage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.binarteamtwo.binarvideoplayer.base.GenericViewModelFactory
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.constant.Constant
import com.binarteamtwo.binarvideoplayer.data.local.room.datasource.LocalMovieDataSource
import com.binarteamtwo.binarvideoplayer.data.network.datasource.MovieDataSource
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.Movie
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieResponse
import com.binarteamtwo.binarvideoplayer.data.network.services.MovieApiServices
import com.binarteamtwo.binarvideoplayer.databinding.FragmentHomePageBinding
import com.binarteamtwo.binarvideoplayer.ui.trailerlist.TrailerListActivity


class HomepageFragment : Fragment(), HomepageContract.View {

    private var isFilteredByFavorite: Boolean = false
    private lateinit var binding: FragmentHomePageBinding
    private lateinit var adapter: HomepageAdapter
    private lateinit var viewModel: HomepageViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }


   /* companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_FAVORITE_VIDEO = "ARG_FAVORITE_VIDEO"


        @JvmStatic
        fun newInstance(isFilterFavorite: Boolean) =
            HomepageFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_FAVORITE_VIDEO, isFilterFavorite)
                }
            }
    } override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isFilteredByFavorite = it.getBoolean(ARG_FAVORITE_VIDEO)
        }
    }*/

    /*override fun onResume() {
        super.onResume()
        getData(isFilteredByFavorite)
    }*/

    //baru

    override fun showMoviePlaylistContent(isContentVisible: Boolean) {
        binding.srlMovie.visibility = if (isContentVisible) View.VISIBLE else View.GONE
    }

    override fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        binding.tvErrorMessage.visibility = if (isErrorEnabled) View.VISIBLE else View.GONE
        binding.tvErrorMessage.text = msg
    }

    override fun setupSwipeRefresh() {
        binding.srlMovie.setOnRefreshListener {
            binding.srlMovie.isRefreshing = false
            viewModel.getMovieData()
        }
    }

    override fun setupList() {
        adapter = HomepageAdapter {movie ->
            val intent = Intent(context,TrailerListActivity::class.java)
            intent.putExtra(Constant.EXTRAS_DATA_MOVIE,movie.id)
            startActivity(intent)
        }
        binding.rvMovieList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@HomepageFragment.adapter
        }
    }

    override fun setListData(data: List<Movie>) {
        adapter.items = data
    }

    override fun initView() {
        setupSwipeRefresh()
        setupList()
    }

    override fun initViewModel() {
        val movieApiServices = MovieApiServices.getInstance()
        movieApiServices?.let {
            val dataSource = MovieDataSource(it)
            val repository = HomepageRepository(dataSource)
            viewModel =
                GenericViewModelFactory(HomepageViewModel(repository)).create(HomepageViewModel::class.java)
        }
        viewModel.movieData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showError(false, null)
                    showMoviePlaylistContent(false)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showError(false, null)
                    showMoviePlaylistContent(true)
                    it.data?.movies?.let { data ->
                        setListData(data)
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showError(true, it.message)
                    showMoviePlaylistContent(false)
                }
            }
        })
        viewModel.getMovieData()
    }
}

/*
private fun initSwipeRefresh() {
    binding.srlMovie.setOnRefreshListener {
        binding.srlMovie.isRefreshing = false
        getMData(isFilteredByFavorite)
    }
}

private fun showDialogDeleteMediaPlaylist(moviePlaylist: MoviePlaylist) {
    val alertDialog: AlertDialog? = activity?.let {
        val builder = AlertDialog.Builder(it)
        builder.apply {
            setTitle("Do you want to delete \"${moviePlaylist.movieTitle}\" ?")
            setPositiveButton("Yes") { dialog, id ->
                viewModel.deleteMoviePlaylist(moviePlaylist)
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

override fun getMovieData() {
    TODO("Not yet implemented")
}*/