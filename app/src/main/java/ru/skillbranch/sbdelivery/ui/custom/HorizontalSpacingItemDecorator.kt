package ru.skillbranch.sbdelivery.ui.custom

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.skillbranch.sbdelivery.util.dpToIntPx

class HorisontalSpacingItemDecorator<in T : ListAdapter<D, VH>, D, VH : RecyclerView.ViewHolder>(
    private val adapter: T,
    private val right: Int = 8
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) != adapter.currentList.lastIndex) {
            outRect.right = view.context.dpToIntPx(right / 2)
        }
    }
}
