package com.tregz.miksing.base

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.tregz.miksing.home.HomeView

abstract class BaseFragment : Fragment() {

    protected lateinit var listener: HomeView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as HomeView
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement ${HomeView::class.java.simpleName}")
        }
    }

    fun snack(view: ViewGroup, text: String) {
        with(Snackbar.make(view,  text, Snackbar.LENGTH_LONG)) {
            setAction("Action", null)
        }.show()
    }

    companion object {
        var TAG = BaseFragment::class.java.simpleName
    }
}