package com.tregz.miksing.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.tregz.miksing.R
import com.tregz.miksing.base.BaseFragment

object HomeNavigation {

    private const val host = R.id.nav_host_fragment

    fun fragmentId(activity: AppCompatActivity): Int? {
        val destination = controller(activity).currentDestination
        return destination?.id
    }

    fun navigate(activity: AppCompatActivity, action: Int) {
        controller(activity).navigate(action)
    }

    fun primary(activity: AppCompatActivity): Fragment? {
        val manager = activity.supportFragmentManager.host()?.childFragmentManager
        return manager?.primaryNavigationFragment
    }

    fun pop(activity: AppCompatActivity) {
        controller(activity).popBackStack()
    }

    private fun controller(activity: AppCompatActivity): NavController {
        return Navigation.findNavController(activity, host)
    }

    private fun FragmentManager.host() : NavHostFragment? {
        return findFragmentById(host) as NavHostFragment?
    }
}
