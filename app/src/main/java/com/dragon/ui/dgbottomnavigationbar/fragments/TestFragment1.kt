package com.dragon.ui.dgbottomnavigationbar.fragments

import android.graphics.Color
import com.dragon.ui.dgbottomnavigationbar.fragments.BaseTestFragment

class TestFragment1: BaseTestFragment() {
    override fun getTitle(): String? {
        return "首页"
    }

    override fun getBackgroundColor(): Int {
        return Color.RED
    }
}