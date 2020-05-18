package com.junemon.gamesapi.feature

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.junemon.gamesapi.R
import com.junemon.gamesapi.core.di.activityComponent
import com.junemon.gamesapi.core.domain.usecase.GameUseCase
import com.junemon.gamesapi.core.model.GamesModel
import com.junemon.gamesapi.databinding.ActivityMainBinding
import com.junemon.gamesapi.util.adapter.AdapterConstant.listGameAdapterCallback
import com.junemon.gamesapi.util.adapter.interfaces.RecyclerHelper
import com.junemon.gamesapi.util.imageHelper.LoadImageHelper
import kotlinx.android.synthetic.main.item_main.view.*
import org.koin.android.ext.android.inject
import org.koin.core.inject
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {
    @Inject
    lateinit var recyclerHelper: RecyclerHelper
    @Inject
    lateinit var loadImageHelper: LoadImageHelper
    @Inject
    lateinit var repo: GameUseCase

    private lateinit var binding: ActivityMainBinding
    private val presenter: MainPresenter by lazy { MainPresenter(this,repo) }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent().inject(this)
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
