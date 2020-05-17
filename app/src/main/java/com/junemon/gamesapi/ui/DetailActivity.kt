package com.junemon.gamesapi.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.junemon.gamesapi.R
import com.junemon.gamesapi.base.BaseActivity
import com.junemon.gamesapi.data.datasource.model.sharedGson.gson
import com.junemon.gamesapi.databinding.ActivityDetailBinding
import com.junemon.gamesapi.model.PublisherPresentation
import com.junemon.gamesapi.util.CommonHelper
import com.junemon.gamesapi.util.CommonHelper.intentKey
import kotlinx.android.synthetic.main.item_games.view.*

/**
 * Created by Ian Damping on 29,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail)
        val data by lazy {
            gson.fromJson(
                intent.getStringExtra(intentKey),
                PublisherPresentation::class.java
            )
        }
        binding.initView(data)
    }

    private fun ActivityDetailBinding.initView(data: PublisherPresentation) {
        apply {
            recyclerviewCatching {
                recyclerViewHelper.run {
                    rvDetail.setUpVerticalListAdapter(data.games,
                        CommonHelper.itemDiffCallbacks, R.layout.item_games, {
                            tvText.text = it.name
                            loadingImageHelper.run {
                                ivImages.loadWithGlide(data.imageBackground)
                            }
                        }
                    )
                }
            }
        }
    }
}