package com.junemon.gamesapi.base

import androidx.appcompat.app.AppCompatActivity
import com.ian.app.helper.interfaces.CommonHelperResult
import com.ian.app.helper.interfaces.LoadImageResult
import com.ian.recyclerviewhelper.interfaces.RecyclerviewHelper
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Ian Damping on 02,December,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
abstract class BaseActivity:AppCompatActivity(),KoinComponent {

    protected val recyclerViewHelper: RecyclerviewHelper by inject()
    protected val loadingImageHelper: LoadImageResult by inject()
    protected val commonHelper: CommonHelperResult by inject()


    protected fun recyclerviewCatching(function: () -> Unit) {
        try {
            function.invoke()
        } catch (e: IllegalArgumentException) {
            commonHelper.timberLogE(e.message)
        }
    }
}