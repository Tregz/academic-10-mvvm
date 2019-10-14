package com.tregz.miksing.base.draw

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build

fun Drawable.filter(color: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) @Suppress(DEPRECATION)
    this.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    else this.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
}

private const val DEPRECATION = "DEPRECATION"