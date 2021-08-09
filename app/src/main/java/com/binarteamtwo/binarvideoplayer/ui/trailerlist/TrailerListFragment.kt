package com.binarteamtwo.binarvideoplayer.ui.trailerlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.binarteamtwo.binarvideoplayer.R
import com.binarteamtwo.binarvideoplayer.base.GenericViewModelFactory
import com.binarteamtwo.binarvideoplayer.base.Resource
import com.binarteamtwo.binarvideoplayer.data.network.datasource.MovieDataSource
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.Movie
import com.binarteamtwo.binarvideoplayer.data.network.services.MovieApiServices
import com.binarteamtwo.binarvideoplayer.databinding.FragmentHomePageBinding
import com.binarteamtwo.binarvideoplayer.databinding.FragmentTrailerListBinding
import com.binarteamtwo.binarvideoplayer.ui.homepage.HomepageAdapter
import com.binarteamtwo.binarvideoplayer.ui.homepage.HomepageRepository
import com.binarteamtwo.binarvideoplayer.ui.homepage.HomepageViewModel

class TrailerListFragment : Fragment(), TrailerListContract.View {

    private lateinit var binding: FragmentTrailerListBinding
    private lateinit var adapter: TrailerListAdapter
    private lateinit var viewModel: TrailerListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTrailerListBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
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
            viewModel.getTrailerData()
        }
    }

    override fun setupList() {
        adapter = TrailerListAdapter {}
        binding.rvTrailerList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@TrailerListFragment.adapter
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
            val repository = TrailerListRepository(dataSource)
            viewModel =
                GenericViewModelFactory(TrailerListViewModel(repository)).create(TrailerListViewModel::class.java)
        }
        viewModel.trailerData.observe(viewLifecycleOwner, {
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
        viewModel.getTrailerData()
    }

}