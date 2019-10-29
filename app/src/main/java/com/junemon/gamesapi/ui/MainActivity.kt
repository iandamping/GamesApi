package com.junemon.gamesapi.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ian.recyclerviewhelper.helper.setUpVerticalListAdapter
import com.junemon.gamesapi.R
import com.junemon.gamesapi.data.ResultToConsume
import com.junemon.gamesapi.data_source.ui.GameDataViewModel
import com.junemon.gamesapi.databinding.ActivityMainBinding
import com.junemon.gamesapi.util.CommonHelper
import com.junemon.gamesapi.util.loadWithGlide
import com.junemon.gamesapi.util.timberLogE
import kotlinx.android.synthetic.main.item_games.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class MainActivity : AppCompatActivity() {
    private val gameVm: GameDataViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this@MainActivity,
            R.layout.activity_main
        )
        binding.apply {
            lifecycleOwner = this@MainActivity
            getData(this)
        }
    }

    private fun getData(binding: ActivityMainBinding) {
        binding.apply {
            gameVm.getListGames().observe(this@MainActivity, Observer { result ->
                when (result.status) {
                    ResultToConsume.Status.LOADING -> {
                        progressBars.visibility = View.VISIBLE
                    }
                    ResultToConsume.Status.ERROR -> {
                        progressBars.visibility = View.GONE
                        timberLogE(result.message)
                    }

                    ResultToConsume.Status.SUCCESS -> {
                        progressBars.visibility = View.GONE
                        rvMain.setUpVerticalListAdapter(result.data?.data,
                            CommonHelper.gamesDiffCallbacks, R.layout.item_games, {
                                tvText.text = it.name
                                ivImages.loadWithGlide(it.shortScreenshots?.get(0)?.image)
                            }
                        )
                    }
                }
            })
        }

    }
}
