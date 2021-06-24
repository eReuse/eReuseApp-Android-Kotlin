package com.example.ereuseapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ereuseapp.ui.device.ComponentsFragment
import com.example.ereuseapp.ui.device.EnvironmentalFragment

class TabsAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
       return when(position) {
           0 -> ComponentsFragment()
           1 -> EnvironmentalFragment()
           else -> Fragment()
       }
    }
}