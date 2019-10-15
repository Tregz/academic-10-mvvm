package com.tregz.miksing.base

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.tregz.miksing.home.MainView

abstract class BaseFragment : Fragment() {

    protected lateinit var listener: MainView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as MainView
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement ${MainView::class.java.simpleName}")
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