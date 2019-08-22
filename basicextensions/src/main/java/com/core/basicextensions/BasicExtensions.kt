package com.core.basicextensions

import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewTreeObserver


fun Boolean.toInt() = if (this) 1 else 0

val Int.toPx: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.toDp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.toPx: Float
    get() = (this / Resources.getSystem().displayMetrics.density)

val Float.toDp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

val Long.secToMs: Long
    get() = (this * 1000)

val Float.secToMs: Long
    get() = (this * 1000).toLong()

val String.packageName: String
    get() = (this.substring(0, this.lastIndexOf(".")))

fun Drawable.maskColor(color: Int) {
    this.mutate()
    this.clearColorFilter()
    this.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
}

fun String.formatAsUrl() =
    if (!this.startsWith("http://") && !this.startsWith("https://")) "http://$this" else this

fun View?.applyGlobalLayoutListener(attachedExpr: (it: View?) -> Unit) {
    this?.viewTreeObserver?.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            this@applyGlobalLayoutListener.viewTreeObserver.removeOnGlobalLayoutListener(this)
            attachedExpr(this@applyGlobalLayoutListener)
        }
    }) ?: attachedExpr(null)
}
