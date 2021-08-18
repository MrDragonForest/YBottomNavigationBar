package com.dragon.ui.bottom_navigation_bar.view

import android.graphics.Color

/***
 *  create by DragonForest on 2021/8/17
 */
class TabStyle {
    var titleColor: Int = Color.BLACK
    var titleSize: Float? = 10f
    var titleText: String = "首页"
    var titleScale: Float? = 1f
    var iconStaticRes: Int = android.R.drawable.ic_btn_speak_now
    var iconSvgaRes: String? = ""
    var overSvgaRes: String? = ""
    var backgroundColor: Int = Color.WHITE
    var isShowSvga = false

    fun titleColor(color: Int): TabStyle {
        this.titleColor = color
        return this
    }

    fun titleSize(size: Float?): TabStyle {
        this.titleSize = size
        return this
    }

    fun titleText(text: String): TabStyle {
        this.titleText = text
        return this
    }

    fun titleScale(scale: Float): TabStyle {
        this.titleScale = scale
        return this
    }

    fun iconStaticRes(res: Int): TabStyle {
        this.iconStaticRes = res
        return this
    }

    fun overSvgaRes(svg: String): TabStyle {
        this.overSvgaRes = svg
        return this
    }

    fun iconSvgaRes(svg: String): TabStyle {
        this.iconSvgaRes = svg
        return this
    }

    fun backgroundColor(color: Int): TabStyle {
        this.backgroundColor = color
        return this
    }

    fun isShowSvga(isShowSvg: Boolean): TabStyle {
        this.isShowSvga = isShowSvg
        return this
    }

    fun copy(): TabStyle {
        var style = TabStyle()
        style.titleColor = this.titleColor
        style.titleSize = this.titleSize
        style.titleText = this.titleText
        style.titleScale = this.titleScale
        style.iconStaticRes = this.iconStaticRes
        style.iconSvgaRes = this.iconSvgaRes
        style.overSvgaRes = this.overSvgaRes
        style.backgroundColor = this.backgroundColor
        style.isShowSvga = this.isShowSvga
        return style
    }
}