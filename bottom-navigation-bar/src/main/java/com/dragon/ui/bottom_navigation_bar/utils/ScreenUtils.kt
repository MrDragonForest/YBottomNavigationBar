package com.dragon.ui.bottom_navigation_bar.utils

import android.content.res.Resources

/***
 *  create by DragonForest on 2021/8/12
 */
object ScreenUtils {

    var density = Resources.getSystem().displayMetrics.density

    fun screenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun screenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun dp2px(dpValue:Number):Int{
        return (dpValue.toFloat()* density).toInt()
    }
}