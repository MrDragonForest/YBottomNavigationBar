package com.dragon.ui.bottom_navigation_bar.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import com.dragon.ui.bottom_navigation_bar.R
import com.dragon.ui.bottom_navigation_bar.utils.ScreenUtils

/***
 *  create by DragonForest on 2021/8/11
 */
class YBottomNavigationBar : FrameLayout {
    private val TAG = this.javaClass.simpleName

    //view控件
    private var view: View? = null
    private var layoutOverlay: FrameLayout? = null
    private var tabContainer: LinearLayout? = null

    //tab集合
    private var bottomNavigationTabList = ArrayList<YBottomNavigationTab>()

    //状态标志
    private var currentSelectPosition = -1
    private var lastSelectPosition = -1

    private var fragmentManager: FragmentManager? = null
    private var containerId: Int = 0

    //监听
    private var selectListener: OnSelectListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        init()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attributeSet, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        view = LayoutInflater.from(context).inflate(R.layout.view_bottom_navigation_bar, this, true)
        layoutOverlay = view?.findViewById(R.id.bottom_navigation_bar_overLay)
        tabContainer = view?.findViewById(R.id.bottom_navigation_bar_item_container)
    }

    fun fragmentManager(fg: FragmentManager?): YBottomNavigationBar {
        this.fragmentManager = fg
        return this
    }

    fun containerId(containerId: Int): YBottomNavigationBar {
        this.containerId = containerId
        return this
    }

    fun addItem(
        tab: YBottomNavigationTab,
        clss: Class<*>,
        tag: String? = null
    ): YBottomNavigationBar {
        tab.tag = tag
        tab.fragmentClass = clss
        bottomNavigationTabList.add(tab)
        return this
    }

    fun firstSelectPosition(pos: Int): YBottomNavigationBar {
        this.currentSelectPosition = pos
        return this
    }

    fun listener(listener: OnSelectListener?): YBottomNavigationBar {
        this.selectListener = listener
        return this
    }

    fun initialise() {
        if (bottomNavigationTabList.size == 0) return
        tabContainer?.removeAllViews()
        var tabWidth = ScreenUtils.screenWidth() / bottomNavigationTabList.size
        bottomNavigationTabList.forEach {
            var lp = ViewGroup.LayoutParams(tabWidth, ViewGroup.LayoutParams.MATCH_PARENT)
            lp.width = tabWidth
            it.layoutParams = lp
            it.position = bottomNavigationTabList.indexOf(it)
            it.setOnClickListener {
                var tab = it as? YBottomNavigationTab ?: return@setOnClickListener
                selectTab(tab.position)
            }
            tabContainer?.addView(it)
            //初始化将所有tab置为未选择状态
            it.onUnSelect()
        }
        //默认选中
        selectTab(currentSelectPosition)
    }

    private fun selectTab(position: Int?) {
        if (position == null) return
        if (position < 0 || position >= bottomNavigationTabList.size) return
        if (lastSelectPosition == -1) {
            //首次
            bottomNavigationTabList[position].onSelect()
            selectListener?.onSelect(position)
            doChangeTab(lastSelectPosition, position)
        } else {
            //非首次
            if (position != currentSelectPosition) {
                bottomNavigationTabList[position].onSelect()
                bottomNavigationTabList[currentSelectPosition].onUnSelect()
                selectListener?.onSelect(position)
                selectListener?.onUnSelect(currentSelectPosition)
                doChangeTab(currentSelectPosition, position)
            } else {
                selectListener?.onReSelect(position)
            }
        }
        lastSelectPosition = currentSelectPosition
        currentSelectPosition = position
        Log.i(TAG, "pos:$position,lastPos:$lastSelectPosition,currentPos:$currentSelectPosition")
    }

    private fun doChangeTab(oldPos: Int, newPos: Int) {
        var newTab = findTabByPosition(newPos)
        var ft = fragmentManager?.beginTransaction()
        if (oldPos != -1) {
            var oldTab = findTabByPosition(oldPos)
            oldTab?.fragment?.let { ft?.detach(it) }
        }
        if (newTab?.fragment == null) {
            newTab?.fragment = fragmentManager?.fragmentFactory?.instantiate(
                context.classLoader,
                newTab?.fragmentClass?.name ?: ""
            )
            newTab?.fragment?.let {
                ft?.add(containerId, it, newTab.tag)
            }
        } else {
            newTab?.fragment?.let {
                ft?.attach(it)
            }
        }
        ft?.commitAllowingStateLoss()
    }

    fun getCurrentTab(): YBottomNavigationTab? {
        return if (currentSelectPosition in 0 until bottomNavigationTabList.size) {
            bottomNavigationTabList[currentSelectPosition]
        } else null
    }

    fun findTabByPosition(pos: Int?): YBottomNavigationTab? {
        if (pos == null) return null
        if (pos >= 0 && pos < bottomNavigationTabList.size) {
            return bottomNavigationTabList[pos]
        }
        return null
    }

    fun findTabByTag(tag: String): YBottomNavigationTab? {
        bottomNavigationTabList.forEach {
            if (it.tag != null && it.tag == tag) {
                return it
            }
        }
        return null
    }

    interface OnSelectListener {
        fun onSelect(position: Int?)
        fun onUnSelect(position: Int?)
        fun onReSelect(position: Int?)
    }
}