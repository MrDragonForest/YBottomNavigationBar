package com.dragon.ui.bottom_navigation_bar.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dragon.ui.bottom_navigation_bar.R
import com.dragon.ui.bottom_navigation_bar.utils.SvgaHelper
import com.opensource.svgaplayer.SVGAImageView

/***
 *  create by DragonForest on 2021/8/12
 */
class YBottomNavigationTab : FrameLayout {
    //view控件
    private var view: View? = null
    private var iconContainer: ViewGroup? = null
    private var iconStatic: ImageView? = null
    private var iconSvga: SVGAImageView? = null
    private var tvTitle: TextView? = null
    private var tvBadge: BadgeTextView? = null

    //样式
    var position: Int = 0
    var tag: String? = null
    var tabActiveStyle: TabStyle? = null
    var tabInActiveStyle: TabStyle? = null

    //状态
    private var setSelected = false
    private var isFirstSet = true

    var fragment: Fragment? = null
    var fragmentClass: Class<*>? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    private fun init() {
        view = LayoutInflater.from(context).inflate(R.layout.view_bottom_navigation_tab, this, true)
        iconContainer = view?.findViewById(R.id.fixed_bottom_navigation_icon_container)
        iconStatic = view?.findViewById(R.id.fixed_bottom_navigation_icon)
        iconSvga = view?.findViewById(R.id.fixed_bottom_navigation_svga)
        tvTitle = view?.findViewById(R.id.fixed_bottom_navigation_title)
        tvBadge = view?.findViewById(R.id.fixed_bottom_navigation_badge)
        //气泡默认样式
        tvBadge?.setTextColor(Color.WHITE)
        tvBadge?.setBackgroundResource(R.drawable.shape_badge)
    }

    fun hasBadge(): Boolean {
        return tvBadge?.visibility == VISIBLE
    }

    fun showBadge(text: String? = null) {
        tvBadge?.visibility = VISIBLE
        if (text.isNullOrEmpty()) {
            //显示红点
            tvBadge?.text = ""
            var lp = tvBadge?.layoutParams as? MarginLayoutParams
            lp?.width = 15
            lp?.height = 15
            lp?.topMargin = 15
            lp?.rightMargin = 15
            tvBadge?.layoutParams = lp
            tvBadge?.setBackgroundResource(R.drawable.shape_badge)
        } else {
            //显示文字
            tvBadge?.text = text
            tvBadge?.setBackgroundResource(R.drawable.shape_badge_text)
        }
    }

    fun hideBadge() {
        tvBadge?.visibility = GONE
    }

    /**
     * 选中样式
     */
    fun onSelect() {
        if (setSelected && !isFirstSet) return
        isFirstSet = false
        setSelected = true
        var style = this.tabActiveStyle ?: return
        view?.setBackgroundColor(style.backgroundColor)

        if (!style.overSvgaRes.isNullOrEmpty()) {
            //有过度动画svga
            iconStatic?.visibility = GONE
            iconSvga?.visibility = VISIBLE
            SvgaHelper.showEffect(
                context,
                style.overSvgaRes!!,
                iconSvga,
                1,
                object : SvgaHelper.SvgaCallback {
                    override fun onParseFinish(isSuccess: Boolean) {

                    }

                    override fun onAnimateFinish(isSuccess: Boolean) {
                        onSelectInternal(style)
                    }
                })

        } else {
            //无过度动画svga
            onSelectInternal(style)
        }

        tvTitle?.text = style.titleText
        style.titleSize?.let { tvTitle?.textSize = it }
        style.titleScale?.let {
            tvTitle?.animate()?.scaleX(it)?.scaleY(it)?.setDuration(100)?.start()
        }
        tvTitle?.setTextColor(style.titleColor)
    }

    private fun onSelectInternal(style: TabStyle) {
        if (style.isShowSvga && !style.iconSvgaRes.isNullOrEmpty()) {
            iconStatic?.visibility = GONE
            iconSvga?.visibility = VISIBLE
            SvgaHelper.showEffect(
                context,
                style.iconSvgaRes!!,
                iconSvga,
                -1,
                null
            )
        } else {
            iconStatic?.setImageResource(style.iconStaticRes)
            iconSvga?.visibility = GONE
            iconStatic?.visibility = VISIBLE
        }

    }

    /**
     * 未选中样式
     */
    fun onUnSelect() {
        if (!setSelected && !isFirstSet) return
        isFirstSet = false
        setSelected = false
        var style = this.tabInActiveStyle ?: return
        view?.setBackgroundColor(style.backgroundColor)
        iconSvga?.stopAnimation()
        if (!style.overSvgaRes.isNullOrEmpty()) {
            //有过度动画svga
            iconStatic?.visibility = GONE
            iconSvga?.visibility = VISIBLE
            SvgaHelper.showEffect(
                context,
                style.overSvgaRes!!,
                iconSvga,
                1,
                object : SvgaHelper.SvgaCallback {
                    override fun onParseFinish(isSuccess: Boolean) {

                    }

                    override fun onAnimateFinish(isSuccess: Boolean) {
                        onUnSelectInternal(style)
                    }
                })

        } else {
            //无过度动画svga
            onUnSelectInternal(style)
        }

        tvTitle?.text = style.titleText
        style.titleSize?.let { tvTitle?.textSize = it }
        style.titleScale?.let {
            tvTitle?.scaleX = it
            tvTitle?.scaleY = it
        }
        tvTitle?.setTextColor(style.titleColor)
    }

    private fun onUnSelectInternal(style: TabStyle) {
        if (style.isShowSvga && !style.iconSvgaRes.isNullOrEmpty()) {
            iconStatic?.visibility = GONE
            iconSvga?.visibility = VISIBLE
            SvgaHelper.showEffect(
                context,
                style.iconSvgaRes!!,
                iconSvga,
                -1,
                null
            )
        } else {
            iconStatic?.setImageResource(style.iconStaticRes)
            iconSvga?.visibility = GONE
            iconStatic?.visibility = VISIBLE
        }
    }


}