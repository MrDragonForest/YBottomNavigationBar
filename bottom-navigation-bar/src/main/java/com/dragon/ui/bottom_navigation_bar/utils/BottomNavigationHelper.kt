package com.dragon.ui.bottom_navigation_bar.utils

/***
 *  create by DragonForest on 2021/8/12
 */
object BottomNavigationHelper {

    /**
     * 获取每个tab的宽度（目前简单实现）
     */
    fun getTabWidth(allSize:Int,tabSize:Int):Int{
        return allSize/tabSize
    }
}