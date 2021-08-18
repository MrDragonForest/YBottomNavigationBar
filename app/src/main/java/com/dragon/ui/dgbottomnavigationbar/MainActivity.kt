package com.dragon.ui.dgbottomnavigationbar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.dragon.ui.bottom_navigation_bar.view.TabStyle
import com.dragon.ui.bottom_navigation_bar.view.YBottomNavigationBar
import com.dragon.ui.bottom_navigation_bar.view.YBottomNavigationTab
import com.dragon.ui.dgbottomnavigationbar.fragments.*

class MainActivity : AppCompatActivity() {

    val TAB_FIRST = "首页"
    val TAB_LOVE = "相亲"
    val TAB_MOMENT = "广场"
    val TAB_MSG = "消息"
    val TAB_MINE = "我的"

    var tv_tps:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_tps = findViewById(R.id.tv_tps)
        setNavigationBar()
    }

    private fun setNavigationBar() {
        var bottomNavigationBar = findViewById<YBottomNavigationBar>(R.id.bottomNavigaitonBar)
        //设置选中和未选中的样式
        var activeStyle = TabStyle()
            .titleColor(Color.RED)
        var inActiveStyle = TabStyle()
            .titleColor(Color.BLACK)

        var tab1 = YBottomNavigationTab(this)
        tab1.tabActiveStyle = activeStyle.copy()
            .titleText("首页")
            .iconStaticRes(R.drawable.mi_tab_item_first)
            .overSvgaRes("mi_tab_item_first_p.svga")
            .titleScale(1.2f)
            .isShowSvga(true)
        tab1.tabInActiveStyle = inActiveStyle.copy()
            .titleText("首页")
            .iconStaticRes(R.drawable.mi_tab_item_first_n)
            .isShowSvga(false)
        var tab2 = YBottomNavigationTab(this)
        tab2.tabActiveStyle = activeStyle.copy()
            .titleText("相亲")
            .iconStaticRes(R.drawable.mi_tab_item_live_love)
            .overSvgaRes("mi_tab_item_live_love_p.svga")
            .isShowSvga(true)
        tab2.tabInActiveStyle = inActiveStyle.copy()
            .titleText("相亲")
            .iconStaticRes(R.drawable.mi_tab_item_live_love_n)
            .isShowSvga(false)
        var tab3 = YBottomNavigationTab(this)
        tab3.tabActiveStyle = activeStyle.copy()
            .titleText("广场")
            .titleScale(1.2f)
            .iconStaticRes(R.drawable.mi_tab_item_moment)
            .overSvgaRes("mi_tab_item_moment_p.svga")
            .isShowSvga(true)
        tab3.tabInActiveStyle = inActiveStyle.copy().titleText("广场1")
            .titleText("广场")
            .iconStaticRes(R.drawable.mi_tab_item_moment_n)
            .isShowSvga(false)
        var tab4 = YBottomNavigationTab(this)
        tab4.tabActiveStyle = activeStyle.copy()
            .titleText("消息")
            .titleScale(1.2f)
            .iconStaticRes(R.drawable.mi_tab_item_msg)
            .overSvgaRes("mi_tab_item_msg_p.svga")
            .isShowSvga(true)
        tab4.tabInActiveStyle = inActiveStyle.copy()
            .titleText("消息")
            .iconStaticRes(R.drawable.mi_tab_item_msg_n)
            .isShowSvga(false)
        var tab5 = YBottomNavigationTab(this)
        tab5.tabActiveStyle = activeStyle.copy()
            .titleText("我的")
            .titleScale(1.2f)
            .iconStaticRes(R.drawable.mi_tab_item_me)
            .overSvgaRes("mi_tab_item_me_p.svga")
            .isShowSvga(true)
        tab5.tabInActiveStyle = inActiveStyle.copy()
            .titleText("我的")
            .iconStaticRes(R.drawable.mi_tab_item_me_n)
            .isShowSvga(false)

        tab4.showBadge("12")
        tab2.showBadge()
        tab5.showBadge()

        //添加选中监听
        var listener = object : YBottomNavigationBar.OnSelectListener {

            override fun onUnSelect(position: Int?) {
                var tab = bottomNavigationBar?.findTabByPosition(position)
                var title = tab?.tabActiveStyle?.titleText
                tv_tps?.text = tv_tps?.text.toString() + "\n取消选择：$title"
            }

            override fun onReSelect(position: Int?) {
                var tab = bottomNavigationBar?.findTabByPosition(position)
                var title = tab?.tabActiveStyle?.titleText
                tv_tps?.text = "当前重复选择$title"
                if(tab?.hasBadge() == true) {
                    tab?.hideBadge()
                } else {
                    tab?.showBadge((0..10).random().toString())
                }
            }

            override fun onSelect(position: Int?) {
                var tab = bottomNavigationBar?.findTabByPosition(position)
                var title = tab?.tabActiveStyle?.titleText
                tv_tps?.text = "当前选择$title"
            }
        }

        //初始化底部tab
        bottomNavigationBar.fragmentManager(supportFragmentManager)
            .containerId(R.id.container)
            .listener(listener)
            .addItem(tab1, TestFragment1::class.java, TAB_FIRST)
            .addItem(tab2, TestFragment2::class.java, TAB_LOVE)
            .addItem(tab3, TestFragment3::class.java, TAB_MOMENT)
            .addItem(tab4, TestFragment4::class.java, TAB_MSG)
            .addItem(tab5, TestFragment5::class.java, TAB_MINE)
            .firstSelectPosition(1)
            .initialise()

    }
}