package com.junemon.gamesapi.feature.detail

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialSharedAxis
import com.junemon.gamesapi.R
import com.junemon.gamesapi.base.BaseFragment
import com.junemon.gamesapi.databinding.FragmentDetailBinding
import com.junemon.gamesapi.feature.viewmodel.GameViewModel
import com.junemon.gamesapi.core.util.EventObserver
import com.junemon.gamesapi.core.util.generateRandomHexColor
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import com.junemon.gamesapi.core.domain.model.ConsumeResult
import com.junemon.gamesapi.core.data.datasource.remote.response.GameDetailResponse
import com.junemon.gamesapi.core.domain.model.GameDetail
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

/**
 * Created by Ian Damping on 09,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class DetailFragment : BaseFragment() {

    private val loadImageHelper: LoadImageHelper by inject()

    private val args: DetailFragmentArgs by navArgs()

    private val gameVm: GameViewModel by lifecycleScope.viewModel(this)

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var idForDeleteItem: Int? = null
    private var isFavorite: Boolean = false
    private var gameToSave:GameDetail? = null
    private val gameId: Int by lazy {
        args.gameId
    }

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
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        binding.ivBack.setOnClickListener {
            navigateUp()
        }
        binding.btnBookmark.setOnClickListener {
            if (isFavorite) {
                if (idForDeleteItem != null) gameVm.clearFavoriteGameById(idForDeleteItem)
            } else {
                gameVm.saveFavoriteGames(gameToSave)
            }
        }
    }

    override fun destroyView() {
        _binding = null
    }

    override fun activityCreated() {
        getData()
        observeState()
    }

    private fun getData() {
        gameVm.getDetailGames(gameId).observe(viewLifecycleOwner, {
            when (it) {
                is ConsumeResult.Loading -> {
                    gameVm.setupProgressBar(false)
                }
                is ConsumeResult.ConsumeData -> {
                    gameToSave = it.data
                    initData(it.data)
                    observeBookmarkState(it.data.name)
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
        gameVm.progressBar.observe(this, EventObserver {
            setDialogShow(it)
        })
    }

    private fun observeBookmarkState(gameName:String){
        gameVm.getFavoriteGames().observe(viewLifecycleOwner){favData ->
            favData.firstOrNull { it.name == gameName }.let { singleData ->
              if (singleData!=null){
                  isFavorite = singleData.name == gameName
                  idForDeleteItem = singleData.gameFavoriteId
              } else{
                  isFavorite = false
                  idForDeleteItem = null
              }
                binding.bookmarkedState = isFavorite
            }
        }
    }

    private fun initData(data: GameDetail) {
        with(binding) {
            loadImageHelper.loadWithGlide(ivDetailImages,data.backgroundImage)
            tvGameDetailName.text = data.name
            tvGameDetailRating.text = data.rating
            chipDetail.chipBackgroundColor =
                ColorStateList.valueOf(Color.parseColor(generateRandomHexColor()))
            if (!data.genres.isNullOrEmpty()){
                chipDetail.text = data.genres
            } else{
                chipDetail.visibility = View.GONE
            }
            htmlReader(tvGameDetail,data.description)
        }
    }
}