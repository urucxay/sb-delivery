package ru.skillbranch.sbdelivery.ui.custom

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.shape.ShapeAppearanceModel
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.util.attrValue
import ru.skillbranch.sbdelivery.util.dpToIntPx
import ru.skillbranch.sbdelivery.util.dpToPx
import ru.skillbranch.sbdelivery.util.setPaddingOptionally
import timber.log.Timber
import kotlin.math.roundToInt

class NumPickerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private val borderWidth = context.dpToPx(1).roundToInt()
    private val viewHeight = context.dpToIntPx(44)
    private val buttonWidth = context.dpToIntPx(30)
    private val textViewWidth = context.dpToIntPx(50)
    private val cornerSize = context.dpToPx(6)
    private val defaultInset = context.dpToIntPx(6) //can't be set programmatically

    private val colorBackground = context.getColor(android.R.color.transparent)
    private val secondaryColor = context.attrValue(R.attr.colorSecondary)
    private val grayColor = context.getColor(R.color.color_gray)

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = grayColor
        strokeWidth = borderWidth.toFloat()
        style = Paint.Style.STROKE
    }

    private val btnMinus: TextView
    private val tvCount: TextView
    private val btnPlus: TextView

    var count = 1
        private set(value) {
            field = value
            invalidate()
        }

    init {
        btnMinus = MaterialButton(context).apply {
            //text
            text = "-"
            textSize = 24f
            setTextColor(secondaryColor)
            setPaddingOptionally(left = 0, right = 0)
            typeface = Typeface.create("sans-serif-light", Typeface.NORMAL)
            //corners
            shapeAppearanceModel = ShapeAppearanceModel.Builder()
                .setTopLeftCornerSize(cornerSize)
                .setBottomLeftCornerSize(cornerSize)
                .build()
            //stroke
            strokeWidth = borderWidth
            strokeColor = ColorStateList.valueOf(grayColor)
            //listener
            setOnClickListener {
                if (count > 1) {
                    count--
                    updateCount(count)
                }
            }
            //background
            setBackgroundColor(colorBackground)
        }
        addView(btnMinus)

        tvCount = TextView(context).apply {
            text = count.toString()
            textSize = 24f
            setTextColor(secondaryColor)
            gravity = Gravity.CENTER
            setBackgroundColor(colorBackground)
        }
        addView(tvCount)

        btnPlus = MaterialButton(context).apply {
            //text
            text = "+"
            textSize = 24f
            setTextColor(secondaryColor)
            setPaddingOptionally(left = 0, right = 0)
            typeface = Typeface.create("sans-serif-light", Typeface.NORMAL)
            //corners
            shapeAppearanceModel = ShapeAppearanceModel.Builder()
                .setTopRightCornerSize(cornerSize)
                .setBottomRightCornerSize(cornerSize)
                .build()
            //stroke
            strokeWidth = borderWidth
            strokeColor = ColorStateList.valueOf(grayColor)
            setOnClickListener {
                count++
                updateCount(count)
                Timber.d("count -> $count")
            }
            //background
            setBackgroundColor(colorBackground)
        }
        addView(btnPlus)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val wms = MeasureSpec.makeMeasureSpec(textViewWidth, MeasureSpec.EXACTLY)
        val hms = MeasureSpec.makeMeasureSpec(viewHeight, MeasureSpec.EXACTLY)
        tvCount.measure(wms, hms)

        val usedWidth = buttonWidth + textViewWidth + buttonWidth
        setMeasuredDimension(usedWidth, viewHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val top = paddingTop
        var left = paddingLeft

        btnMinus.layout(
            left,
            top - defaultInset,
            left + buttonWidth,
            top + viewHeight + defaultInset
        )

        left = btnMinus.right
        tvCount.layout(
            left,
            top,
            left + textViewWidth,
            top + viewHeight
        )

        left = tvCount.right
        btnPlus.layout(
            left,
            top - defaultInset,
            left + buttonWidth,
            top + viewHeight + defaultInset
        )
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        //draw line above count textView
        canvas.drawLine(
            buttonWidth.toFloat(),
            0F + borderWidth / 2F,
            (canvas.width - buttonWidth).toFloat(),
            0F + borderWidth / 2F,
            linePaint
        )
        //draw line below count textView
        canvas.drawLine(
            buttonWidth.toFloat(),
            canvas.height - borderWidth / 2F,
            (canvas.width - buttonWidth).toFloat(),
            canvas.height - borderWidth / 2F,
            linePaint
        )
    }

    private fun updateCount(count: Int) {
        tvCount.text = count.toString()
//        tvCount.invalidate()
    }

    //region Saving state
    override fun onSaveInstanceState(): Parcelable? {
        val savedState = SavedState(super.onSaveInstanceState())
        savedState.count = count
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is SavedState) {
            count = state.count
        }
        super.onRestoreInstanceState(state)
    }

    private class SavedState : BaseSavedState, Parcelable {
        var count = 0

        constructor(superState: Parcelable?) : super(superState)

        constructor(src: Parcel) : super(src) {
            count = src.readInt()
        }

        override fun writeToParcel(dst: Parcel, flags: Int) {
            super.writeToParcel(dst, flags)
            dst.writeInt(count)
        }

        override fun describeContents() = 0

        companion object CREATOR : Parcelable.Creator<SavedState> {
            override fun createFromParcel(parcel: Parcel) = SavedState(parcel)
            override fun newArray(size: Int): Array<SavedState?> = arrayOfNulls(size)
        }
    }
    //endregion

}