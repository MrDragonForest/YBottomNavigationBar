package com.dragon.ui.dgbottomnavigationbar.fragments

import android.graphics.Color
import com.dragon.ui.dgbottomnavigationbar.fragments.BaseTestFragment

class TestFragment3: BaseTestFragment() {
    override fun getTitle(): String? {
        return "广场"
    }

    override fun getBackgroundColor(): Int {
        return Color.CYAN
    }
}