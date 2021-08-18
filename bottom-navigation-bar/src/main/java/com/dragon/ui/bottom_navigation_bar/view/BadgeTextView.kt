package com.dragon.ui.bottom_navigation_bar.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/***
 *  create by DragonForest on 2021/8/16
 */
class BadgeTextView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    AppCompatTextView(context!!, attrs, defStyleAttr) {
    private var mAreDimensOverridden = false
    private var mDesiredWidth = 100
    private var mDesiredHeight = 100
    private fun init() {
        // method stub
    }

    /**
     * clear's all previous set values
     */
    fun clearPrevious() {
        mAreDimensOverridden = false
    }

    /**
     * if width and height of the view needs to be changed
     *
     * @param width new width that needs to be set
     * @param height new height that needs to be set
     */
    fun setDimens(width: Int, height: Int) {
        mAreDimensOverridden = true
        mDesiredWidth = width
        mDesiredHeight = height
        requestLayout()
    }

    /**
     * invalidate's view so badgeItem can draw again
     */
    fun recallOnDraw() {
        invalidate()
    }

    /**
     * {@inheritDoc}
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (mAreDimensOverridden) {
            val widthMode = MeasureSpec.getMode(widthMeasureSpec)
            val widthSize = MeasureSpec.getSize(widthMeasureSpec)
            val heightMode = MeasureSpec.getMode(heightMeasureSpec)
            val heightSize = MeasureSpec.getSize(heightMeasureSpec)
            val width: Int
            val height: Int
            width = when (widthMode) {
                MeasureSpec.EXACTLY ->                     //Must be this size
                    widthSize
                MeasureSpec.AT_MOST ->                     //Can't be bigger than...
                    Math.min(mDesiredWidth, widthSize)
                MeasureSpec.UNSPECIFIED ->                     //Be whatever you want
                    mDesiredWidth
                else -> mDesiredWidth
            }
            height = when (heightMode) {
                MeasureSpec.EXACTLY ->                     //Must be this size
                    heightSize
                MeasureSpec.AT_MOST ->                     //Can't be bigger than...
                    Math.min(mDesiredHeight, heightSize)
                MeasureSpec.UNSPECIFIED ->                     //Be whatever you want
                    mDesiredHeight
                else -> mDesiredHeight
            }

            //MUST CALL THIS
            setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    init {
        init()
    }
}
