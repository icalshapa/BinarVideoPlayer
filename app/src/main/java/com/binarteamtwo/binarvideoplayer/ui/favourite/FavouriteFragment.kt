package com.binarteamtwo.binarvideoplayer.ui.favourite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.binarteamtwo.binarvideoplayer.base.GenericViewModelFactory
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.constant.Constant

import com.binarteamtwo.binarvideoplayer.data.network.datasource.MovieDataSource
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.Movie
import com.binarteamtwo.binarvideoplayer.data.network.services.MovieApiServices
import com.binarteamtwo.binarvideoplayer.databinding.FragmentFavouritePageBinding
import com.binarteamtwo.binarvideoplayer.ui.trailerlist.TrailerListActivity


class FavouriteFragment: Fragment(), FavouriteContract.View {
    private var isFilteredByFavorite: Boolean = false
    private lateinit var binding: FragmentFavouritePageBinding
    private lateinit var adapter: FavouriteAdapter
    private lateinit var viewModel: FavouriteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }



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
            viewModel.getMovieDataPopular()
        }
    }

    override fun setupList() {
        adapter = FavouriteAdapter {movie ->
            val intent = Intent(context, TrailerListActivity::class.java)
            intent.putExtra(Constant.EXTRAS_DATA_MOVIE,movie.id)
            startActivity(intent)}
        binding.rvMovieList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@FavouriteFragment.adapter
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
            val repository = FavouriteRepository(dataSource)
            viewModel = GenericViewModelFactory(FavouriteViewModel(repository))
                .create(FavouriteViewModel::class.java)
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
        viewModel.getMovieDataPopular()
    }
}

