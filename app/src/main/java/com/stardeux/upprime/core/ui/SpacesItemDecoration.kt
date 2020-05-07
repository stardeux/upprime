package com.stardeux.upprime.core.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        val layoutManager = parent.layoutManager
        when(layoutManager){
            is GridLayoutManager -> {
                val position = parent.getChildAdapterPosition(view) // item position
                val column = position % layoutManager.spanCount // item column

                outRect.left = space - column * space / layoutManager.spanCount
                outRect.right = (column + 1) * space / layoutManager.spanCount

                if (position < layoutManager.spanCount) { // top edge
                    outRect.top = space
                }
                outRect.bottom = space // item bottom

            }
            is LinearLayoutManager -> {
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.top = space
                }
                outRect.left = space
                outRect.right = space
                outRect.bottom = space
            }
        }
    }
}