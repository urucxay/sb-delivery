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
import ru.skillbranch.sbdelivery.util.dpToIntPx


/**
 * Not working correctly yet. It works only when stepSize = 1 and draws only full star.
 * Otherwise draws rating wrong. But for me it works.
 */
class SvgRatingBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.ratingBarStyle
) : AppCompatRatingBar(context, attrs, defStyleAttr) {

    private var elementBitmap: Bitmap? = null

    //space between stars
    private val spacing = context.dpToIntPx(25)

    init {
        progressDrawable = makeTiledDrawable(progressDrawable, false) as LayerDrawable
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        elementBitmap?.let {
            val width = it.width * numStars - spacing  //delete last spacing
            setMeasuredDimension(width, it.height)
        }
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
                val tileBitmap = drawable.bitmap
                elementBitmap = null ?: tileBitmap

                val shapeDrawable = ShapeDrawable()
                val bitmapShader = BitmapShader(
                    tileBitmap,
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
            drawable.intrinsicWidth + spacing,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return BitmapDrawable(resources, bitmap)
    }
}