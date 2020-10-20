package com.junemon.gamesapi.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.junemon.gamesapi.base.BaseFragment
import com.junemon.gamesapi.core.cache.model.GameEntity
import com.junemon.gamesapi.databinding.FragmentHomeBinding
import com.junemon.gamesapi.feature.genre.GenrePagerAdapter
import com.junemon.gamesapi.feature.viewmodel.GameViewModel
import com.junemon.gamesapi.feature.viewmodel.SharedViewModel
import com.junemon.gamesapi.util.EventObserver
import com.junemon.gamesapi.util.horizontalRecyclerviewInitializer
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import com.junemon.gamesapi.util.viewModelProvider
import com.junemon.model.ConsumeCacheResult
import com.junemon.model.ConsumeResult
import javax.inject.Inject

/**
 * Created by Ian Damping on 08,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class HomeFragment : BaseFragment(), HomeSliderAdapter.HomeSliderAdapterListener {

    @Inject
    lateinit var loadImageHelper: LoadImageHelper

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val sharedVm: SharedViewModel by activityViewModels()
    private lateinit var genrePagerAdapter: GenrePagerAdapter
    private lateinit var homeAdapter: HomeSliderAdapter
    private lateinit var gameVm: GameViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeAdapter = HomeSliderAdapter(this, loadImageHelper)
        genrePagerAdapter = GenrePagerAdapter(this)
        gameVm = viewModelProvider(viewModelFactory)
        return binding.root
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        binding.initView()
    }

    override fun destroyView() {
        _binding = null
    }

    override fun activityCreated() {
        getGames()
        getGenres()
        observeState()
    }

    private fun getGenres() {
        gameVm.getListGamesByGenres().observe(viewLifecycleOwner, { result ->
            when (result) {
                is ConsumeResult.Loading -> {
                    gameVm.setupProgressBar(false)
                }
                is ConsumeResult.ConsumeData -> {
                    sharedVm.setGames(result.data)
                    TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
                        tab.text = result.data[position].name
                    }.attach()
                }
                is ConsumeResult.ErrorHappen -> {
                    onFailGetValue(result.exception)
                }
                is ConsumeResult.Complete -> {
                    gameVm.setupProgressBar(true)
                }
            }

        })
    }

    private fun getGames() {
        gameVm.getCachedListGames().observe(viewLifecycleOwner, {
            when (it) {
                is ConsumeCacheResult.Loading -> {
                    gameVm.setupProgressBar(false)
                    if (it.cache!=null){
                        homeAdapter.run {
                            submitList(it.cache)
                            // Force a redraw in case the time zone has changed
                            this.notifyDataSetChanged()
                        }
                        gameVm.setupProgressBar(true)
                    }
                }
                is ConsumeCacheResult.ConsumeData -> {
                    homeAdapter.run {
                        submitList(it.data)
                        // Force a redraw in case the time zone has changed
                        this.notifyDataSetChanged()
                    }
                }
                is ConsumeCacheResult.ErrorHappen -> {
                    onFailGetValue(it.exception)
                }
                is ConsumeCacheResult.Complete -> {
                    gameVm.setupProgressBar(true)
                }
            }
        })
    }

    private fun observeState() {
        gameVm.progressBar.observe(viewLifecycleOwner, EventObserver {
            if (!it) {
                binding.shimmerSlider.apply {
                    visibility = View.VISIBLE
                    startShimmer()
                }
                binding.rvGames.visibility = View.GONE
            } else {
                binding.shimmerSlider.apply {
                    visibility = View.GONE
                    stopShimmer()
                }
                binding.rvGames.visibility = View.VISIBLE
            }
        })
    }

    private fun FragmentHomeBinding.initView() {
        pager.adapter = genrePagerAdapter
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        rvGames.apply {
            horizontalRecyclerviewInitializer()
            adapter = homeAdapter
        }
        ivMain.setOnClickListener{
            setupExitEnterAxisTransition()
            val directions =HomeFragmentDirections.actionHomeFragmentToPagingFragment()
            navigate(directions)
        }
        btnSearchMain.setOnClickListener {
            setupExitEnterAxisTransition()
            val directions = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            navigate(directions)
        }
    }

    override fun onClicked(data: GameEntity) {
        setupExitEnterAxisTransition()
        val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data.gameId)
        navigate(directions)
    }
}