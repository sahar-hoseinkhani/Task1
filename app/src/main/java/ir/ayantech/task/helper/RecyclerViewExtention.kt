package ir.ayantech.task.helper

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.gridSetup(
    spanCount: Int = 5,
    useCustomGrid: Boolean = false
) {
    layoutManager = GridLayoutManager(context, spanCount, RecyclerView.VERTICAL, false)
    if (useCustomGrid) {
        val lookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    0 -> 2
                    1 -> 3
                    else -> 1
                }
            }
        }
        (layoutManager as GridLayoutManager).spanSizeLookup = lookup
    }
}

fun RecyclerView.verticalSetup() {
    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
}