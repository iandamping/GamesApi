package com.junemon.gamesapi.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.junemon.gamesapi.R
import com.junemon.gamesapi.base.BaseFragment
import com.junemon.gamesapi.databinding.FragmentHomeBinding
import com.junemon.gamesapi.feature.viewmodel.GameViewModel
import com.junemon.gamesapi.util.adapter.AdapterConstant.listGameAdapterCallback
import com.junemon.gamesapi.util.adapter.interfaces.RecyclerHelper
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import com.junemon.gamesapi.util.viewModelProvider
import com.junemon.model.ConsumeResult
import kotlinx.android.synthetic.main.item_main_bind.view.*
import javax.inject.Inject


/**
 * Created by Ian Damping on 08,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class HomeFragment : BaseFragment() {
    @Inject
    lateinit var recyclerHelper: RecyclerHelper
    @Inject
    lateinit var loadImageHelper: LoadImageHelper
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var gameVm: GameViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        gameVm = viewModelProvider(viewModelFactory)
        return binding.root
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
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
                    onSuccessGetGame(it.data)
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

    private fun observeState(){
        gameVm.progressBar.observe(this,{
            if (it){
                binding.progressBars.visibility = View.GONE
            }else{
                binding.progressBars.visibility = View.VISIBLE
            }
        })
    }

    private fun onFailGetValue(e: Exception) {
        Snackbar.make(binding.root, e.message ?: "Error happen", Snackbar.LENGTH_SHORT).show()
    }

    private fun onSuccessGetGame(data: List<com.junemon.model.games.GamesModel>) {
        recyclerHelper.run {
            binding.rvGames.setUpVerticalListAdapter(items = data,
                diffUtil = listGameAdapterCallback,
                layoutResId = R.layout.item_main,
                bindHolder = {
                    tvText.text = it.name
                    loadImageHelper.run {
                        ivImages.loadWithGlide(it.backgroundImage)
                    }
                }, itemClick = {
                    gameVm.saveGames(this)
                }
            )
        }
    }
}