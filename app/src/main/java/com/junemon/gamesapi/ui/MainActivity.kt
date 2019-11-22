package com.junemon.gamesapi.ui

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ian.app.helper.data.ResultToConsume
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.helper.util.myToast
import com.ian.recyclerviewhelper.helper.setUpVerticalListAdapter
import com.junemon.gamesapi.R
import com.junemon.gamesapi.databinding.ActivityMainBinding
import com.junemon.gamesapi.model.mapToPresentation
import com.junemon.gamesapi.util.CommonHelper
import kotlinx.android.synthetic.main.item_games.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import android.view.animation.OvershootInterpolator
import androidx.core.view.ViewCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.visible

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
            initView(this)
        }
    }

    private fun getData(binding: ActivityMainBinding) {
        binding.apply {
            gameVm.get().observe(this@MainActivity, Observer { result ->
                when (result.status) {
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
            })
        }
    }

    private fun initView(binding: ActivityMainBinding) {
        val fabCloseAnimation: Animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.close_fab_anim)
        val fabOpenAnimation: Animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.open_fab_anim)
        binding.apply {
            fabTest.setOnClickListener {
                fabTest.startAnimation(fabCloseAnimation)
                val dialogBuilder = BottomSheetDialog(this@MainActivity)
                dialogBuilder.setContentView(R.layout.testings)
                dialogBuilder.window?.attributes?.windowAnimations = R.style.DialogAnimation
                dialogBuilder.show()

                dialogBuilder.setOnDismissListener {
                    fabTest.startAnimation(fabOpenAnimation)
                }
            }
        }



    }

    fun scale(view: View, delay: Long) {
        view.scaleX = 0f
        view.scaleY = 0f
        view.animate()
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(500)
            .setStartDelay(delay)
            .setInterpolator(OvershootInterpolator())
            .start()
    }
}
