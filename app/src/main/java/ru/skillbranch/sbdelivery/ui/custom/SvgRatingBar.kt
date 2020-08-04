package ru.skillbranch.sbdelivery.ui.custom

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Shader
import android.graphics.drawable.*
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatRatingBar
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.util.dpToPx
import kotlin.math.roundToInt

/**
 * Not working correctly yet. It works only when stepSize = 1 and draws only full star.
 * Otherwise draws rating wrong. But for me it works.
 */
class SvgRatingBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.ratingBarStyle
) : AppCompatRatingBar(context, attrs, defStyleAttr) {

    //space between stars
    private var spacing = context.dpToPx(4)
    //single icon(star) size
    private var iconWidth = context.dpToPx(18)
    private var iconHeight = context.dpToPx(18)

    init {
        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.SvgRatingBar, defStyleAttr, 0)
        spacing = attributes.getDimension(R.styleable.SvgRatingBar_srb_spacing, spacing)
        iconWidth = attributes.getDimension(R.styleable.SvgRatingBar_srb_icon_width, iconWidth)
        iconHeight = attributes.getDimension(R.styleable.SvgRatingBar_srb_icon_height, iconHeight)
        attributes.recycle()
        progressDrawable = makeTiledDrawable(progressDrawable, false) as LayerDrawable
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = (iconWidth + spacing) * numStars - spacing//delete last spacing
        setMeasuredDimension(width.roundToInt(), iconHeight.toInt())
    }

    /**
     * Converts a drawable to a tiled version of itself. It will recursively
     * traverse layer and state list drawables.
     * It replaces drawable with bitmapDrawable which can be drawn correctly.
     */
    private fun makeTiledDrawable(drawable: Drawable, clip: Boolean): Drawable {

        when (drawable) {
            is LayerDrawable -> {
                //amount subdrawables in the progressDrawable's layer-list
                //usually equals 3 - background, secondaryProgress, progress
                val numberOfLayers = drawable.numberOfLayers
                val outDrawables = arrayOfNulls<Drawable>(numberOfLayers)

                for (i in 0 until numberOfLayers) {
                    val id = drawable.getId(i)
                    //fill outDrawables with converted bitmapDrawables
                    outDrawables[i] = makeTiledDrawable(
                        drawable.getDrawable(i),
                        id == android.R.id.progress || id == android.R.id.secondaryProgress
                    )
                }

                val resultDrawable = LayerDrawable(outDrawables)

                //set default ids for new layerDrawable's elements
                for (i in 0 until numberOfLayers) {
                    resultDrawable.setId(i, drawable.getId(i))
                }

                return resultDrawable
            }
            is BitmapDrawable -> {

                val shapeDrawable = ShapeDrawable()
                val bitmapShader = BitmapShader(
                    drawable.bitmap,
                    Shader.TileMode.REPEAT,
                    Shader.TileMode.CLAMP
                )
                shapeDrawable.paint.shader = bitmapShader
                shapeDrawable.paint.colorFilter = drawable.paint.colorFilter
                return if (clip) {
                    ClipDrawable(
                        shapeDrawable, Gravity.START,
                        ClipDrawable.HORIZONTAL
                    )
                } else
                    shapeDrawable
            }
            else -> {
                return makeTiledDrawable(getBitmapDrawableFromVectorDrawable(drawable), clip)
            }
        }

    }

    //create new bitmapDrawable from drawable
    private fun getBitmapDrawableFromVectorDrawable(drawable: Drawable): BitmapDrawable {
        val bitmap = Bitmap.createBitmap(
            (iconWidth + spacing).roundToInt(),
            iconHeight.roundToInt(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, iconWidth.roundToInt(), iconHeight.roundToInt())
        drawable.draw(canvas)
        return BitmapDrawable(resources, bitmap)
    }
}