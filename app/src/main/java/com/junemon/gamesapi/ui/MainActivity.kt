package com.junemon.gamesapi.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ian.app.helper.data.ResultToConsume
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.helper.util.myToast
import com.ian.app.helper.util.timberLogE
import com.ian.recyclerviewhelper.helper.setUpVerticalListAdapter
import com.junemon.gamesapi.R
import com.junemon.gamesapi.databinding.ActivityMainBinding
import com.junemon.gamesapi.model.mapToPresentation
import com.junemon.gamesapi.util.CommonHelper
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
            gameVm.get {result ->
                when(result.status){
                    ResultToConsume.Status.LOADING -> {
                        progressBars.visibility = View.VISIBLE
                    }
                    ResultToConsume.Status.ERROR -> {
                        progressBars.visibility = View.GONE
                        myToast(result.message)
                    }

                    ResultToConsume.Status.SUCCESS -> {
                        progressBars.visibility = View.GONE
                        rvMain.setUpVerticalListAdapter(result.data?.mapToPresentation(),
                            CommonHelper.gamesDiffCallbacks, R.layout.item_games, {
                                tvText.text = it.name
                                ivImages.loadWithGlide(it.backgroundImage)
                            }
                        )
                    }
                }
            }
        }
    }
}
