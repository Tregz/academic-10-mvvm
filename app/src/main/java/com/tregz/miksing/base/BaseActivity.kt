package com.tregz.miksing.base

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

abstract class BaseActivity : AppCompatActivity() {

    private val dialogs: MutableList<BaseDialog> = mutableListOf()

    val group: ViewGroup?
        get() = window.decorView.findViewById(android.R.id.content)

    override fun onDestroy() {
        for (dialog in dialogs) dialog.alert?.dismiss()
        super.onDestroy()
    }

    fun add(dialog: BaseDialog): BaseDialog {
        dialogs.add(dialog)
        return dialog
    }

    private fun drawable(resource: Int): Drawable? {
        return ContextCompat.getDrawable(this, resource)
    }

    companion object {
        //private val TAG: String = BaseActivity::class.java.simpleName
    }
}