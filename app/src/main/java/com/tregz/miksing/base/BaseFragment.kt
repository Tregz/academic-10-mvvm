package com.tregz.miksing.base

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    fun snack(view: ViewGroup, text: String) {
        with(Snackbar.make(view,  text, Snackbar.LENGTH_LONG)) {
            setAction("Action", null)
        }.show()
    }

    companion object {
        var TAG = BaseFragment::class.java.simpleName
    }
}