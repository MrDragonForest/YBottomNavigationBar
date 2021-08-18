package com.dragon.ui.dgbottomnavigationbar.fragments

import android.graphics.Color
import com.dragon.ui.dgbottomnavigationbar.fragments.BaseTestFragment

class TestFragment2: BaseTestFragment() {
    override fun getTitle(): String? {
        return "相亲"
    }

    override fun getBackgroundColor(): Int {
        return Color.BLUE
    }
}