package com.junemon.gamesapi.feature

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.junemon.gamesapi.databinding.ActivityMainBinding
import com.junemon.gamesapi.util.FLAGS_FULLSCREEN
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}
