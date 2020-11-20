package com.junemon.gamesapi.favorite.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.junemon.gamesapi.base.BaseFragment
import com.junemon.gamesapi.core.R
import com.junemon.gamesapi.core.domain.model.GameFavorite
import com.junemon.gamesapi.core.util.mapToData
import com.junemon.gamesapi.favorite.databinding.FragmentDetailFavoriteBinding
import com.junemon.gamesapi.favorite.feature.vm.GameFavoriteViewModel
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Ian Damping on 20,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class FavoriteDetailFragment : BaseFragment() {
    private val loadImageHelper: LoadImageHelper by inject()

    private val args: FavoriteDetailFragmentArgs by navArgs()

    private val gameVm: GameFavoriteViewModel by viewModel()

    private var _binding: FragmentDetailFavoriteBinding? = null
    private val binding get() = _binding!!

    private var idForDeleteItem: Int? = null
    private var isFavorite: Boolean = false

    private val gameData: GameFavorite by lazy {
        Gson().fromJson(args.passedFavoriteGame, GameFavorite::class.java)
    }

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            loadImageHelper.loadWithGlide(ivDetailImages, gameData.backgroundImage)
            tvGameDetailName.text = gameData.name
            tvGameDetailRating.text = gameData.rating
            htmlReader(tvGameDetail, gameData.description)
        }

        binding.ivBack.setOnClickListener {
            navigateUp()
        }
        binding.btnBookmark.setOnClickListener {
            if (isFavorite) {
                gameVm.clearFavoriteGameById(gameData.gameFavoriteId)
            } else {
                gameVm.saveFavoriteGames(gameData.mapToData())
            }
        }
    }

    override fun destroyView() {
        _binding = null
    }

    override fun activityCreated() {
        observeBookmarkState()
    }

    private fun observeBookmarkState() {
        gameVm.getFavoriteGames().observe(viewLifecycleOwner) { favData ->
            favData.firstOrNull { it.name == gameData.name }.let { singleData ->
                if (singleData != null) {
                    isFavorite = singleData.name == gameData.name
                    idForDeleteItem = singleData.gameFavoriteId
                } else {
                    isFavorite = false
                    idForDeleteItem = null
                }
                initBookmarkHelper(isFavorite)
            }
        }
    }

    private fun initBookmarkHelper(state: Boolean) {
        if (state) {
            binding.btnBookmark.setImageResource(R.drawable.ic_bookmarked)
        } else binding.btnBookmark.setImageResource(R.drawable.ic_unbookmark)
    }
}