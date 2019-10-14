package com.tregz.miksing.base

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.tregz.miksing.R
import com.tregz.miksing.base.draw.filter

abstract class BaseDialog(private val context: Context) : DialogInterface.OnShowListener {

    var alert: AlertDialog? = null
    val builder: AlertDialog.Builder = AlertDialog.Builder(context, R.style.AlertDialogWrap)
    open val okay: String = context.getString(R.string.ok)

    override fun onShow(dialog: DialogInterface?) {
        // TODO
    }

    fun exit() {
        alert?.apply {
            if (isShowing) dismiss()
            cancel()
        }
    }

    fun show() {
        if (context is Activity && !context.isDestroyed) builder.apply {
            setNegativeButton(R.string.cancel) { _, _ -> exit() }
            setNegativeButtonIcon(icon(R.drawable.ic_close, R.color.secondaryDark))
            setPositiveButtonIcon(icon(R.drawable.ic_check, R.color.primaryPage))
            alert = create().apply {
                setOnShowListener(this@BaseDialog)
                show()
            }
        }
    }

    private fun icon(resource: Int, tint: Int): Drawable? {
        return ContextCompat.getDrawable(context, resource)?.apply {
            filter(ContextCompat.getColor(context, tint))
        }
    }

    protected fun inflate(layout: Int, viewGroup: ViewGroup): View {
        return LayoutInflater.from(context).inflate(layout, viewGroup, false)
    }
}