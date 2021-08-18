package com.dragon.ui.dgbottomnavigationbar.fragments

import android.graphics.Color
import com.dragon.ui.dgbottomnavigationbar.fragments.BaseTestFragment

class TestFragment4: BaseTestFragment() {
    override fun getTitle(): String? {
        return "消息"
    }

    override fun getBackgroundColor(): Int {
        return Color.GRAY
    }
}