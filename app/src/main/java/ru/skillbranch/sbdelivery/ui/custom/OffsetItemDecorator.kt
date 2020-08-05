package ru.skillbranch.sbdelivery.ui.custom

import android.graphics.Rect
import android.view.View
import androidx.annotation.Dimension
import androidx.annotation.Dimension.DP
import androidx.recyclerview.widget.RecyclerView
import ru.skillbranch.sbdelivery.util.dpToIntPx

class OffsetItemDecorator(
    @Dimension(unit = DP)
    private val left: Int = 0,
    @Dimension(unit = DP)
    private val top: Int = 0,
    @Dimension(unit = DP)
    private val right: Int = 0,
    @Dimension(unit = DP)
    private val bottom: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val childCount = state.itemCount

        outRect.left = if (position > 0) view.context.dpToIntPx(left) else 0
        outRect.right = if (position < childCount) view.context.dpToIntPx(right) else 0

        outRect.top = if (position > 0) view.context.dpToIntPx(top) else 0
        outRect.bottom = if (position < childCount) view.context.dpToIntPx(bottom) else 0
    }
}
