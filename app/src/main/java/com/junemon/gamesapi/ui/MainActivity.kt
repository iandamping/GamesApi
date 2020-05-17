package com.junemon.gamesapi.ui

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.ian.app.helper.data.ResultToConsume
import com.ian.app.helper.util.startActivity
import com.junemon.gamesapi.R
import com.junemon.gamesapi.base.BaseActivity
import com.junemon.gamesapi.data.datasource.model.sharedGson.gson
import com.junemon.gamesapi.databinding.ActivityMainBinding
import com.junemon.gamesapi.model.mapListToPresentation
import com.junemon.gamesapi.model.mapToPresentation
import com.junemon.gamesapi.ui.vm.CreatorDataViewModel
import com.junemon.gamesapi.ui.vm.GameDataViewModel
import com.junemon.gamesapi.ui.vm.PublisherDataViewModel
import com.junemon.gamesapi.util.CommonHelper
import com.junemon.gamesapi.util.CommonHelper.intentKey
import com.junemon.model.RepositoryDataHelper
import kotlinx.android.synthetic.main.item_games.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class MainActivity : BaseActivity() {
    private val creatorVm:CreatorDataViewModel by viewModel()
    private val gameVm: GameDataViewModel by viewModel()
    private val publisherVm: PublisherDataViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this@MainActivity,
            R.layout.activity_main
        )
        binding.apply {
            lifecycleOwner = this@MainActivity
            getGame()
            initView(this)
        }
        creatorVm.getCreator()
    }

    private fun ActivityMainBinding.getGame() {
        apply {
            gameVm.get().observe(this@MainActivity, Observer { result ->
                when(result){
                    is RepositoryDataHelper.Error ->{
                        if (progressBars.isVisible) progressBars.visibility = View.GONE
                        Snackbar.make(rootLayout, result.exception.toString(), Snackbar.LENGTH_SHORT).show()
                        recyclerviewCatching {
                            recyclerViewHelper.apply {
                                rvMain.setUpVerticalListAdapter(result.cache?.mapToPresentation(),
                                    CommonHelper.gamesDiffCallbacks, R.layout.item_games, {
                                        tvText.text = it.name
                                        loadingImageHelper.apply {ivImages.loadWithGlide(it.backgroundImage) }
                                    }
                                )
                            }
                        }
                    }
                    is RepositoryDataHelper.Success ->{
                        if (progressBars.isVisible) progressBars.visibility = View.GONE
                        recyclerviewCatching {
                            recyclerViewHelper.apply {
                                rvMain.setUpVerticalListAdapter(result.data.mapToPresentation(),
                                    CommonHelper.gamesDiffCallbacks, R.layout.item_games, {
                                        tvText.text = it.name
                                        loadingImageHelper.apply {ivImages.loadWithGlide(it.backgroundImage) }
                                    }
                                )
                            }
                        }
                    }
                    is RepositoryDataHelper.Loading ->{
                        progressBars.visibility = View.VISIBLE
                    }
                }

                /*when (result.status) {
                    ResultToConsume.Status.LOADING -> {
                        progressBars.visibility = View.VISIBLE
                    }
                    ResultToConsume.Status.ERROR -> {
                        if (progressBars.isVisible) progressBars.visibility = View.GONE
                        Snackbar.make(rootLayout, result.message!!, Snackbar.LENGTH_SHORT).show()
                    }

                    ResultToConsume.Status.SUCCESS -> {
                        if (progressBars.isVisible) progressBars.visibility = View.GONE
                    }
                }
                recyclerviewCatching {
                    recyclerViewHelper.apply {
                        rvMain.setUpVerticalListAdapter(result.data?.mapToPresentation(),
                            CommonHelper.gamesDiffCallbacks, R.layout.item_games, {
                                tvText.text = it.name
                                loadingImageHelper.apply {ivImages.loadWithGlide(it.backgroundImage) }
                            }
                        )
                    }
                }*/

            })
        }
    }

    private fun ActivityMainBinding.getPublisher(){
        publisherVm.get().observe(this@MainActivity, Observer { result ->
            when (result.status) {
                ResultToConsume.Status.LOADING -> {
                    progressBars.visibility = View.VISIBLE
                }
                ResultToConsume.Status.ERROR -> {
                    if (progressBars.isVisible) progressBars.visibility = View.GONE
                    Snackbar.make(rootLayout, result.message!!, Snackbar.LENGTH_SHORT).show()
                }

                ResultToConsume.Status.SUCCESS -> {
                    if (progressBars.isVisible) progressBars.visibility = View.GONE
                }
            }
            try {
                recyclerViewHelper.apply {
                    rvMain.setUpVerticalListAdapter(result.data?.mapListToPresentation(),
                        CommonHelper.publisherDiffCallbacks, R.layout.item_games, {
                            tvText.text = it.name
                            loadingImageHelper.apply { ivImages.loadWithGlide(it.imageBackground) }
                        },{
                            startActivity<DetailActivity>{
                                putExtra(intentKey, gson.toJson(this@setUpVerticalListAdapter))
                            }
                        }
                    )
                }
            }catch (e:Exception){
                commonHelper.timberLogE(e.message)
            }
        })

    }

    private fun ActivityMainBinding.getCreator(){

        creatorVm.creatorData.observe(this@MainActivity, Observer { result ->
            when (result.status) {
                ResultToConsume.Status.LOADING -> {
                    progressBars.visibility = View.VISIBLE
                }
                ResultToConsume.Status.ERROR -> {
                    if (progressBars.isVisible) progressBars.visibility = View.GONE
                    Snackbar.make(rootLayout, result.message!!, Snackbar.LENGTH_SHORT).show()
                }

                ResultToConsume.Status.SUCCESS -> {
                    if (progressBars.isVisible) progressBars.visibility = View.GONE
                }
            }
            try {
                recyclerViewHelper.apply {
                    rvMain.setUpVerticalListAdapter(result.data,
                        CommonHelper.creatorDiffCallbacks, R.layout.item_games, {
                            tvText.text = it.next
                        },{
                            startActivity<DetailActivity>{
                                putExtra(intentKey, gson.toJson(this@setUpVerticalListAdapter))
                            }
                        }
                    )
                }
            }catch (e:Exception){
                commonHelper.timberLogE(e.message)
            }
        })

    }

    private fun initView(binding: ActivityMainBinding) {
        val fabCloseAnimation: Animation =
            AnimationUtils.loadAnimation(this@MainActivity, R.anim.close_fab_anim)
        val fabOpenAnimation: Animation =
            AnimationUtils.loadAnimation(this@MainActivity, R.anim.open_fab_anim)
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

}
