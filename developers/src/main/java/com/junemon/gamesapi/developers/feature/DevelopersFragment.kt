package com.junemon.gamesapi.developers.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.junemon.gamesapi.base.BaseFragment
import com.junemon.gamesapi.core.data.model.DeveloperData
import com.junemon.gamesapi.core.presentation.imageHelper.LoadImageHelper
import com.junemon.gamesapi.core.util.verticalRecyclerviewInitializer
import com.junemon.gamesapi.developers.databinding.FragmentDeveloperBinding
import com.junemon.gamesapi.developers.viewmodel.DevelopersViewModel
import com.junemon.gamesapi.di.injectData
import com.junemon.model.ConsumeResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope

/**
 * Created by Ian Damping on 22,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class DevelopersFragment : BaseFragment(), DeveloperAdapter.DeveloperAdapterListener {

    private val loadImageHelper: LoadImageHelper by inject()
    private val developerVm: DevelopersViewModel by lifecycleScope.inject()

    private lateinit var devAdapter: DeveloperAdapter
    private var _binding: FragmentDeveloperBinding? = null
    private val binding get() = _binding!!

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectData()
    }

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeveloperBinding.inflate(inflater, container, false)
        devAdapter = DeveloperAdapter(this, loadImageHelper)
        return binding.root
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        binding.initView()
    }

    override fun destroyView() {
        _binding = null
    }

    override fun activityCreated() {
        getDev()
    }

    override fun onClicked(data: DeveloperData) {
        TODO("Not yet implemented")
    }

    private fun FragmentDeveloperBinding.initView() {
        rvDeveloper.apply {
            verticalRecyclerviewInitializer()
            adapter = devAdapter
        }
    }

    private fun getDev() {
        developerVm.getListDevelopers().observe(viewLifecycleOwner, {
            when (it) {
                is ConsumeResult.Loading -> {
                    developerVm.setupProgressBar(false)
                }
                is ConsumeResult.ConsumeData -> {
                    devAdapter.run {
                        submitList(it.data)
                        // Force a redraw in case the time zone has changed
                        this.notifyDataSetChanged()
                    }
                }
                is ConsumeResult.ErrorHappen -> {
                    onFailGetValue(it.exception)
                }
                is ConsumeResult.Complete -> {
                    developerVm.setupProgressBar(true)
                }
            }
        })
    }
}