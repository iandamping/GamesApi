package com.junemon.gamesapi.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.junemon.gamesapi.R
import com.junemon.gamesapi.core.model.GamesModel
import com.junemon.gamesapi.databinding.ActivityMainBinding
import com.junemon.gamesapi.util.adapter.AdapterConstant.listGameAdapterCallback
import com.junemon.gamesapi.util.adapter.interfaces.RecyclerHelper
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import kotlinx.android.synthetic.main.item_main.view.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainView {
    private val recyclerHelper: RecyclerHelper by inject()
    private val loadImageHelper: LoadImageHelper by inject()
    private lateinit var binding: ActivityMainBinding
    private val presenter: MainPresenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            presenter.getGames()
        }
    }

    override fun onFailGetValue(e: Exception) {
        binding.progressBars.visibility = View.GONE
        Snackbar.make(binding.root, e.message!!, Snackbar.LENGTH_SHORT).show()
    }

    override fun onSuccessGetGame(data: List<GamesModel>) {
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
                }
            )
        }
    }
}
