package com.junemon.gamesapi.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.junemon.gamesapi.base.BaseFragment
import com.junemon.gamesapi.databinding.FragmentHomeBinding
import com.junemon.gamesapi.feature.viewmodel.GameViewModel
import com.junemon.gamesapi.util.adapter.interfaces.RecyclerHelper
import com.junemon.gamesapi.util.horizontalRecyclerviewInitializer
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import com.junemon.gamesapi.util.viewModelProvider
import com.junemon.model.ConsumeResult
import com.junemon.model.games.GameData
import javax.inject.Inject


/**
 * Created by Ian Damping on 08,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class HomeFragment : BaseFragment(), HomeSliderAdapter.HomeSliderAdapterListener {
    @Inject
    lateinit var recyclerHelper: RecyclerHelper

    @Inject
    lateinit var loadImageHelper: LoadImageHelper

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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
        homeAdapter = HomeSliderAdapter(this,loadImageHelper)
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
        observeState()
    }

    private fun getGames() {
        gameVm.getGames().observe(viewLifecycleOwner, {
            when (it) {
                is ConsumeResult.Loading -> {
                    gameVm.setupProgressBar(false)
                }
                is ConsumeResult.ConsumeData -> {
                    homeAdapter.run {
                        submitList(it.data)
                        // Force a redraw in case the time zone has changed
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
        gameVm.progressBar.observe(this, {
            setDialogShow(it)
        })
    }


    private fun FragmentHomeBinding.initView() {
        rvGames.apply {
            horizontalRecyclerviewInitializer()
            adapter = homeAdapter
        }
    }

    override fun onClicked(data: GameData) {
        setupExitEnterAxisTransition()
       val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data.id)
        navigate(directions)
    }
}