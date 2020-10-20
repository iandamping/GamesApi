package com.junemon.gamesapi.feature.genre

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


/**
 * Created by Ian Damping on 10,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GenrePagerAdapter (fragment:Fragment):FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
       return 18
    }

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = GenreFragment()
        fragment.arguments = Bundle().apply {
            putInt("object", position)
        }
        return fragment
    }

}