package ir.ayantech.task.helper

import android.view.View

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.changeVisibility(show: Boolean) {
    if (show) makeVisible() else makeGone()
}