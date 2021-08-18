package com.dragon.ui.dgbottomnavigationbar.fragments

import android.graphics.Color
import com.dragon.ui.dgbottomnavigationbar.fragments.BaseTestFragment

class TestFragment5: BaseTestFragment() {
    override fun getTitle(): String? {
        return "我的"
    }

    override fun getBackgroundColor(): Int {
        return Color.GREEN
    }
}