package com.junemon.gamesapi.favorite.feature.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.google.gson.Gson
import com.junemon.gamesapi.base.BaseFragment
import com.junemon.gamesapi.core.domain.model.GameFavorite
import com.junemon.gamesapi.core.util.verticalRecyclerviewInitializer
import com.junemon.gamesapi.favorite.databinding.FragmentFavoriteBinding
import com.junemon.gamesapi.favorite.feature.vm.GameFavoriteViewModel
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Ian Damping on 20,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class FavoriteFragment : BaseFragment(), FavoriteAdapter.FavoriteAdapterListener {
    private val loadImageHelper: LoadImageHelper by inject()
    private val gameVm: GameFavoriteViewModel by viewModel()
    private lateinit var favoriteGameAdapter: FavoriteAdapter
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        favoriteGameAdapter = FavoriteAdapter(this, loadImageHelper)
        return binding.root
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            rvFavorite.apply {
                verticalRecyclerviewInitializer()
                adapter = favoriteGameAdapter
            }
        }
    }

    override fun destroyView() {
        _binding = null
    }

    override fun activityCreated() {
        gameVm.getFavoriteGames().observe(this) {
            favoriteGameAdapter.run {
                submitList(it)
            }
            binding.tvFavorite.isVisible = it.isNullOrEmpty()
        }
    }

    override fun onClicked(data: GameFavorite) {
        val direction = FavoriteFragmentDirections.actionFavoriteFragmentToFavoriteDetailFragment(
            Gson().toJson(data)
        )
        navigate(direction)
    }
}