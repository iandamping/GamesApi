package com.junemon.gamesapi.feature.detail

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.transition.MaterialSharedAxis
import com.junemon.gamesapi.R
import com.junemon.gamesapi.base.BaseFragment
import com.junemon.gamesapi.databinding.FragmentDetailBinding
import com.junemon.gamesapi.feature.viewmodel.GameViewModel
import com.junemon.gamesapi.util.EventObserver
import com.junemon.gamesapi.util.generateRandomHexColor
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import com.junemon.gamesapi.util.viewModelProvider
import com.junemon.model.ConsumeResult
import com.junemon.model.games.GameDetail
import javax.inject.Inject


/**
 * Created by Ian Damping on 09,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class DetailFragment : BaseFragment() {

    @Inject
    lateinit var loadImageHelper: LoadImageHelper

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val args: DetailFragmentArgs by navArgs()

    private lateinit var gameVm: GameViewModel
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
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
        gameVm = viewModelProvider(viewModelFactory)
        return binding.root
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
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
                is ConsumeResult.ConsumeSingleData -> {
                    initData(it.data)
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
        gameVm.progressBar.observe(this,EventObserver {
            setDialogShow(it)
        })
    }

    private fun initData(data: GameDetail) {
        binding.run {
            loadImageHelper.loadWithGlide(ivDetailImages,data.backgroundImage)
            tvGameDetailName.text = data.name
            tvGameDetailRating.text = data.rating.toString()
            chipDetail.chipBackgroundColor =
                ColorStateList.valueOf(Color.parseColor(generateRandomHexColor()))
            if (!data.genres.isNullOrEmpty()){
                chipDetail.text = data.genres?.get(0)?.name
            } else{
                chipDetail.visibility = View.GONE
            }
            htmlReader(tvGameDetail,data.description)
        }
    }
}