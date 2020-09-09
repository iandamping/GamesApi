package com.junemon.gamesapi.base

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import com.junemon.gamesapi.R
import com.junemon.gamesapi.databinding.CustomLoadingBinding
import com.junemon.gamesapi.util.layoutInflater
import dagger.android.support.DaggerFragment

/**
 * Created by Ian Damping on 05,August,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
abstract class BaseFragment : DaggerFragment() {
    private var _binding: CustomLoadingBinding? = null
    private val binding get() = _binding!!
    private lateinit var alert: AlertDialog

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setBaseDialog(context)
        // dont use this, but i had to
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }

    protected fun navigate(destination: NavDirections, extraInfo: FragmentNavigator.Extras) =
        with(findNavController()) {
            currentDestination?.getAction(destination.actionId)
                ?.let { navigate(destination, extraInfo) }
        }

    protected fun navigate(destination: NavDirections) =
        with(findNavController()) {
            currentDestination?.getAction(destination.actionId)
                ?.let { navigate(destination) }
        }



    protected fun setupExitEnterTransition() {
        enterTransition = MaterialFadeThrough().apply {
            duration = resources.getInteger(R.integer.motion_duration_xsmall).toLong()
        }
        exitTransition = Hold().apply {
            duration = resources.getInteger(R.integer.motion_duration_xsmall).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.motion_duration_xsmall).toLong()
        }

    }

    protected fun setupExitEnterAxisTransition() {
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = resources.getInteger(R.integer.motion_duration_large).toLong()
        }
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = resources.getInteger(R.integer.motion_duration_large).toLong()
        }
    }

    private fun setBaseDialog(context: Context) {
        _binding = CustomLoadingBinding.inflate(context.layoutInflater)

        val dialogBuilder = AlertDialog.Builder(context).run {
            setView(binding.root)
        }

        alert = dialogBuilder.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }

        binding.run {
            lottieAnimation.enableMergePathsForKitKatAndAbove(true)
        }
    }

    protected fun setDialogShow(status: Boolean) {
        if (status) {
            alert.dismiss()
        } else {
            alert.show()
        }
    }


    abstract fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    abstract fun viewCreated(view: View, savedInstanceState: Bundle?)

    abstract fun destroyView()

    abstract fun activityCreated()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityCreated()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        destroyView()
        _binding = null
    }

    protected fun onFailGetValue(e: Exception) {
        Snackbar.make(binding.root, e.message ?: "Error happen", Snackbar.LENGTH_SHORT).show()
    }

    protected fun toastingMessage(message:String){
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }
}