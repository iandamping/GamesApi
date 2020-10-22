package com.junemon.gamesapi.feature.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.transition.MaterialSharedAxis
import com.junemon.gamesapi.R
import com.junemon.gamesapi.base.BaseFragment
import com.junemon.gamesapi.databinding.FragmentSearchBinding
import com.junemon.gamesapi.feature.viewmodel.GameViewModel
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import com.junemon.gamesapi.util.viewModelProvider
import com.junemon.model.ConsumeResult
import com.junemon.gamesapi.core.data.model.GameSearch
import com.junemon.gamesapi.util.gridRecyclerviewInitializer
import javax.inject.Inject


/**
 * Created by Ian Damping on 10,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class SearchFragment : BaseFragment(), SearchAdapter.SearchAdapterListener {
    @Inject
    lateinit var loadImageHelper: LoadImageHelper

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var gameVm: GameViewModel
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = resources.getInteger(R.integer.motion_duration_large).toLong()
        }
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = resources.getInteger(R.integer.motion_duration_large).toLong()
        }
    }

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        searchAdapter = SearchAdapter(this, loadImageHelper)
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
    }

    private fun FragmentSearchBinding.initView() {
        rvSearchPlace.apply {
            gridRecyclerviewInitializer(2)
            adapter = searchAdapter
        }


        searchViews.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!searchViews.isIconified) {
                    searchViews.isIconified = true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchGame(newText)
                return false
            }
        })
    }

    private fun searchGame(s: String?) {
        if (!s.isNullOrEmpty()) {
            gameVm.getSearchGames(s).observe(viewLifecycleOwner, {
                when (it) {

                    is ConsumeResult.ConsumeData -> {
                        if (it.data.isEmpty()) {
                            binding.lnSearchFailed.visibility = View.VISIBLE
                            binding.rvSearchPlace.visibility = View.GONE
                        } else {
                            searchAdapter.run {
                                submitList(it.data)
                                // Force a redraw in case the time zone has changed
                                this.notifyDataSetChanged()
                            }

                            binding.lnSearchFailed.visibility = View.GONE
                            binding.rvSearchPlace.visibility = View.VISIBLE
                        }
                    }
                    is ConsumeResult.ErrorHappen -> {
                        onFailGetValue(it.exception)
                    }

                }

            })
        }
    }



    override fun onClicked(data: GameSearch) {
        setupExitEnterAxisTransition()
        val directions = SearchFragmentDirections.actionSearchFragmentToDetailFragment(data.id)
        navigate(directions)
    }
}