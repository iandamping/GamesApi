package com.junemon.gamesapi.feature.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import com.junemon.gamesapi.R
import com.junemon.gamesapi.databinding.FragmentGenreBinding
import com.junemon.gamesapi.databinding.FragmentHomeBinding
import com.junemon.gamesapi.feature.home.HomeFragmentDirections
import com.junemon.gamesapi.feature.viewmodel.SharedViewModel
import com.junemon.gamesapi.util.gridRecyclerviewInitializer
import com.junemon.gamesapi.util.horizontalRecyclerviewInitializer
import com.junemon.model.games.GamesItem


/**
 * Created by Ian Damping on 10,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GenreFragment : Fragment(), GenreRecycleAdapter.GenreRecycleAdapterListener {
    private val sharedVm: SharedViewModel by activityViewModels()
    private var _binding: FragmentGenreBinding? = null
    private val binding get() = _binding!!
    private val genreAdapter:GenreRecycleAdapter = GenreRecycleAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initView()
        requireArguments().takeIf { it.containsKey("object") }?.apply {
            sharedVm.listGameGenre.observe(viewLifecycleOwner, {
                genreAdapter.run {
                    submitList(it[getInt("object")].games)
                    // Force a redraw in case the time zone has changed
                    this.notifyDataSetChanged()
                }
            })
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun FragmentGenreBinding.initView() {
        rvGenres.apply {
            gridRecyclerviewInitializer(2)
            adapter = genreAdapter
        }
    }

    override fun onClicked(data: GamesItem) {
        setupExitEnterAxisTransition()
        val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data.id)
        navigate(directions)
    }

    private fun setupExitEnterAxisTransition() {
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = resources.getInteger(R.integer.motion_duration_large).toLong()
        }
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = resources.getInteger(R.integer.motion_duration_large).toLong()
        }
    }

    private fun navigate(destination: NavDirections) =
        with(findNavController()) {
            currentDestination?.getAction(destination.actionId)
                ?.let { navigate(destination) }
        }

}