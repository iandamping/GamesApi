package com.junemon.gamesapi.feature.paging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.junemon.gamesapi.base.BaseFragment
import com.junemon.gamesapi.databinding.FragmentPagingBinding
import com.junemon.gamesapi.feature.viewmodel.GameViewModel
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import com.junemon.gamesapi.core.domain.model.Game
import com.junemon.gamesapi.core.util.verticalRecyclerviewInitializer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.androidx.scope.lifecycleScope as koinLifecycleScope

/**
 * Created by Ian Damping on 20,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PagingFragment : BaseFragment(), PagingAdapter.PagingAdapterListener {
    private val loadImageHelper: LoadImageHelper by inject()
    private val gameVm: GameViewModel by koinLifecycleScope.viewModel(this)


    private lateinit var pagingAdapter: PagingAdapter
    private var _binding: FragmentPagingBinding? = null
    private val binding get() = _binding!!

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPagingBinding.inflate(inflater, container, false)
        pagingAdapter = PagingAdapter(this, loadImageHelper)
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

    override fun onClicked(data: Game) {
        val directions = PagingFragmentDirections.actionPagingFragmentToDetailFragment(data.gameId)
        navigate(directions)
    }

    private fun getPaging() {
        lifecycleScope.launch {
            gameVm.getPagingListGames().collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

    private fun FragmentPagingBinding.initView() {
        with(rvGames) {
            verticalRecyclerviewInitializer()
            adapter = pagingAdapter
            pagingAdapter.addLoadStateListener { loadState ->

                // Only show the list if refresh succeeds.
                isVisible = loadState.source.refresh is LoadState.NotLoading
                // Show loading spinner during initial load or refresh.
                shimmerSlider.isVisible = loadState.source.refresh is LoadState.Loading
            }
        }
        btnSearchMain.setOnClickListener {
            setupExitEnterAxisTransition()
            val directions = PagingFragmentDirections.actionPagingFragmentToSearchFragment()
            navigate(directions)
        }
    }
}