package ru.skillbranch.sbdelivery.ui.custom

import android.graphics.Rect
import android.view.View
import androidx.annotation.Dimension
import androidx.annotation.Dimension.DP
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.skillbranch.sbdelivery.util.dpToIntPx
import timber.log.Timber

class GridItemDecorator(
    @Dimension(unit = DP)
    private val spacing: Int = 0,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        val totalSpanCount = getTotalSpanCount(parent)
        val spanSize = getItemSpanSize(parent, position)

        val spacingInDp = view.context.dpToIntPx(spacing)

        Timber.d("totalSpanCount -> $totalSpanCount")
        Timber.d("spanSize -> $spanSize")

        outRect.top = if (isInTheFirstRow(position, totalSpanCount)) 0 else spacingInDp
        outRect.left = if (isFirstInRow(position, totalSpanCount, spanSize)) 0 else spacingInDp / 2
        outRect.right = if (isLastInRow(position, totalSpanCount, spanSize)) 0 else spacingInDp / 2
        outRect.bottom = 0
    }

    private fun isInTheFirstRow(position: Int, totalSpanCount: Int): Boolean =
        position < totalSpanCount

    private fun isFirstInRow(position: Int, totalSpanCount: Int, spanSize: Int): Boolean =
        if (totalSpanCount != spanSize) {
            position % totalSpanCount == 0
        } else true

    private fun isLastInRow(position: Int, totalSpanCount: Int, spanSize: Int): Boolean =
        isFirstInRow(position + 1, totalSpanCount, spanSize)

    private fun getTotalSpanCount(parent: RecyclerView): Int =
        (parent.layoutManager as? GridLayoutManager)?.spanCount ?: 1

    private fun getItemSpanSize(parent: RecyclerView, position: Int): Int =
        (parent.layoutManager as? GridLayoutManager)?.spanSizeLookup?.getSpanSize(position) ?: 1

}

