package com.junemon.gamesapi.feature

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.junemon.gamesapi.R
import com.junemon.gamesapi.core.di.activityComponent
import com.junemon.gamesapi.core.model.ConsumeResult
import com.junemon.gamesapi.core.model.GamesModel
import com.junemon.gamesapi.databinding.ActivityMainBinding
import com.junemon.gamesapi.feature.viewmodel.GameViewModel
import com.junemon.gamesapi.util.adapter.AdapterConstant.listGameAdapterCallback
import com.junemon.gamesapi.util.adapter.interfaces.RecyclerHelper
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import kotlinx.android.synthetic.main.item_main.view.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var recyclerHelper: RecyclerHelper

    @Inject
    lateinit var loadImageHelper: LoadImageHelper

    @Inject
    lateinit var gameVm: GameViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent().inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getGames()

    }

    private fun getGames() {
        gameVm.getGames().observe(this@MainActivity, Observer {
            when (it) {
                is ConsumeResult.ConsumeData -> {
                    onSuccessGetGame(it.data)
                }
                is ConsumeResult.ErrorHappen -> {
                    onFailGetValue(it.exception)
                }
            }
        })
    }

    private fun getCachedGames() {
        Timber.e(" this is the data ${gameVm.getCachedGame()}")
    }

    private fun onFailGetValue(e: Exception) {
        binding.progressBars.visibility = View.GONE
        Snackbar.make(binding.root, e.message ?: "Error happen", Snackbar.LENGTH_SHORT).show()
    }

    private fun onSuccessGetGame(data: List<GamesModel>) {
        binding.progressBars.visibility = View.GONE
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
                , onLongClicks = {
                    getCachedGames()
                }
            )
        }
    }
}
