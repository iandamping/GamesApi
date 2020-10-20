package com.junemon.gamesapi.feature.paging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.junemon.gamesapi.base.BaseFragment
import com.junemon.gamesapi.databinding.FragmentPagingBinding
import com.junemon.gamesapi.feature.home.HomeFragmentDirections
import com.junemon.gamesapi.feature.viewmodel.GameViewModel
import com.junemon.gamesapi.util.gridRecyclerviewInitializer
import com.junemon.gamesapi.util.horizontalRecyclerviewInitializer
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import com.junemon.gamesapi.util.viewModelProvider
import com.junemon.model.games.GameData
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ian Damping on 20,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PagingFragment : BaseFragment(), PagingAdapter.PagingAdapterListener {
    @Inject
    lateinit var loadImageHelper: LoadImageHelper

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var pagingAdapter: PagingAdapter
    private lateinit var gameVm: GameViewModel
    private var _binding: FragmentPagingBinding? = null
    private val binding get() = _binding!!

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPagingBinding.inflate(inflater, container, false)
        pagingAdapter = PagingAdapter(this, loadImageHelper)
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
       getPaging()
    }

    override fun onClicked(data: GameData) {
        Timber.e("clicked at : $data")
    }

    private fun getPaging() {
       lifecycleScope.launch {
            gameVm.getPagingListGames().collectLatest {
                with(binding){
                    shimmerSlider.apply {
                        visibility = View.GONE
                        stopShimmer()
                    }
                    rvGames.visibility = View.VISIBLE
                }
                pagingAdapter.submitData(it)
            }
        }
    }

    private fun FragmentPagingBinding.initView() {
        shimmerSlider.apply {
            visibility = View.VISIBLE
            startShimmer()
        }
        rvGames.visibility = View.GONE


        rvGames.apply {
            gridRecyclerviewInitializer(2)
            adapter = pagingAdapter
        }
        btnSearchMain.setOnClickListener {
            setupExitEnterAxisTransition()
            val directions = PagingFragmentDirections.actionPagingFragmentToSearchFragment()
            navigate(directions)
        }
    }
}