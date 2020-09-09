package com.junemon.gamesapi.feature

import android.os.Bundle
import com.junemon.gamesapi.databinding.ActivityMainBinding
import com.junemon.gamesapi.util.FLAGS_FULLSCREEN
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val IMMERSIVE_FLAG_TIMEOUT = 500L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        // Before setting full screen flags, we must wait a bit to let UI settle; otherwise, we may
        // be trying to set app to immersive mode before it's ready and the flags do not stick
        binding.root.postDelayed({
            binding.root.systemUiVisibility = FLAGS_FULLSCREEN
        }, IMMERSIVE_FLAG_TIMEOUT)
    }

}
