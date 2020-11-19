package com.junemon.gamesapi.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.junemon.gamesapi.base.BaseFragment
import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity
import com.junemon.gamesapi.databinding.FragmentHomeBinding
import com.junemon.gamesapi.feature.genre.GenrePagerAdapter
import com.junemon.gamesapi.feature.viewmodel.GameViewModel
import com.junemon.gamesapi.feature.viewmodel.SharedViewModel
import com.junemon.gamesapi.core.util.EventObserver
import com.junemon.gamesapi.core.util.horizontalRecyclerviewInitializer
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import com.junemon.gamesapi.core.util.loadingVisibility
import com.junemon.gamesapi.core.domain.model.ConsumeCacheResult
import com.junemon.gamesapi.core.domain.model.ConsumeResult
import com.junemon.gamesapi.core.domain.model.Game
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope

/**
 * Created by Ian Damping on 08,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class HomeFragment : BaseFragment(), HomeSliderAdapter.HomeSliderAdapterListener {

    private val loadImageHelper: LoadImageHelper by inject()
    private val sharedVm: SharedViewModel by activityViewModels()
    private val gameVm: GameViewModel by lifecycleScope.inject()

    private lateinit var genrePagerAdapter: GenrePagerAdapter
    private lateinit var homeAdapter: HomeSliderAdapter
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
                            // Force a redraw
                            // this.notifyDataSetChanged()
                        }
                    }
                }
                is ConsumeCacheResult.ConsumeData -> {
                    homeAdapter.run {
                        submitList(it.data)
                        // Force a redraw
                        // this.notifyDataSetChanged()
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
            with(binding){
                shimmerSlider.loadingVisibility = it
                rvGames.isVisible = it
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

        tvPaging.setOnClickListener{
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

    override fun onClicked(data: Game) {
        setupExitEnterAxisTransition()
        val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data.gameId)
        navigate(directions)
    }
}