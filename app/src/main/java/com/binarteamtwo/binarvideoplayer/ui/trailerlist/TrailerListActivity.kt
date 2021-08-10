package com.binarteamtwo.binarvideoplayer.ui.trailerlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.base.GenericViewModelFactory
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.constant.Constant
import com.binarteamtwo.binarvideoplayer.data.network.datasource.MovieDataSource
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.Movie
import com.binarteamtwo.binarvideoplayer.data.network.services.MovieApiServices
import com.binarteamtwo.binarvideoplayer.databinding.ActivityTrailerListBinding
import com.binarteamtwo.binarvideoplayer.ui.homepage.HomepageAdapter

class TrailerListActivity : AppCompatActivity(), TrailerListContract.View{

    private lateinit var binding: ActivityTrailerListBinding
    private lateinit var viewModel: TrailerListViewModel
    private lateinit var adapter: TrailerListAdapter
    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

    }

    override fun showContent(isContentVisible: Boolean) {
        binding.srlTrailer.visibility = if (isContentVisible) View.VISIBLE else View.GONE
    }

    override fun showLoading(isLoading: Boolean) {
        binding.progressBarTrailer.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        binding.tvErrorMessageTrailer.visibility = if (isErrorEnabled) View.VISIBLE else View.GONE
        binding.tvErrorMessageTrailer.text = msg
    }

    override fun setupSwipeRefresh() {
        binding.srlTrailer.setOnRefreshListener {
            binding.srlTrailer.isRefreshing = false
            viewModel.getTrailerData(movieId)
        }
    }

    override fun setupList() {
        adapter = TrailerListAdapter {

        }
        binding.rvTrailerList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@TrailerListActivity.adapter
        }
    }

    override fun setListData(data: List<Movie>) {
        adapter.items = data
    }

    override fun getIntentData() {
        movieId = intent.getIntExtra(Constant.EXTRAS_DATA_MOVIE, 0)
    }

    override fun initView() {
        binding = ActivityTrailerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        initViewModel()
        setupSwipeRefresh()
        setupList()
    }

    override fun initViewModel() {
        val movieApiServices = MovieApiServices.getInstance()
        movieApiServices?.let {
            val dataSource = MovieDataSource(it)
            val repository = TrailerListRepository(dataSource)
            viewModel = GenericViewModelFactory(TrailerListViewModel(repository)).create(TrailerListViewModel::class.java)
        }
        viewModel.trailerData.observe(this, {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showError(false, null)
                    showContent(false)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showError(false, null)
                    showContent(true)
                    it.data?.movies?.let { data ->
                        setListData(data)
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showError(true, it.message)
                    showContent(false)
                }
            }
        })
        viewModel.getTrailerData(movieId)
    }
}
