package com.junemon.gamesapi.feature.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.junemon.gamesapi.base.BaseFragment
import com.junemon.gamesapi.core.domain.model.ConsumeResult
import com.junemon.gamesapi.core.domain.model.Game
import com.junemon.gamesapi.core.util.EventObserver
import com.junemon.gamesapi.core.util.horizontalRecyclerviewInitializer
import com.junemon.gamesapi.core.util.loadingVisibility
import com.junemon.gamesapi.databinding.FragmentHomeBinding
import com.junemon.gamesapi.feature.viewmodel.GameViewModel
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

/**
 * Created by Ian Damping on 08,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@FlowPreview
@ExperimentalCoroutinesApi
class HomeFragment : BaseFragment(), HomeSliderAdapter.HomeSliderAdapterListener {

    private val loadImageHelper: LoadImageHelper by inject()
    private val gameVm: GameViewModel by lifecycleScope.viewModel(this)

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
        observeState()
    }

    private fun getGames() {
        gameVm.getCachedListGames().observe(viewLifecycleOwner, {
            when (it) {
                is ConsumeResult.Loading -> {
                    gameVm.setupProgressBar(false)
                }
                is ConsumeResult.ConsumeData -> {
                    with(homeAdapter){
                        submitList(it.data)
                        // Force a redraw
                        this.notifyDataSetChanged()
                    }
                }
                is ConsumeResult.ErrorHappen -> {
                    onFailGetValue(it.exception)
                }
                is ConsumeResult.Complete -> {
                    gameVm.setupProgressBar(true)
                }
            }
        })
    }

    private fun observeState() {
        gameVm.progressBar.observe(viewLifecycleOwner, EventObserver {
            with(binding) {
                shimmerSlider.loadingVisibility = it
                rvGames.isVisible = it
            }
        })
    }

    private fun FragmentHomeBinding.initView() {
        rvGames.apply {
            horizontalRecyclerviewInitializer()
            adapter = homeAdapter
        }

        tvPaging.setOnClickListener {
            setupExitEnterAxisTransition()
            val directions = HomeFragmentDirections.actionHomeFragmentToPagingFragment()
            navigate(directions)
        }

        tvFavorite.setOnClickListener {
            val uri = Uri.parse(gameVm.getFavoriteUri())
            startActivity(Intent(Intent.ACTION_VIEW, uri))
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