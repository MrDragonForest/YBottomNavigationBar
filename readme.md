## 自定义底部tab

### 解决了什么问题？
- 1.实现底部tab文字,图标选中和未选中切换

- 2.支持svga动画过度

- 3.支持消息角标

- 4.支持每个tab自定义样式,文字颜色,大小,图标等,并支持动态修改;

- 5.支持与fragment绑定

- 6.可监听选中,重复选中,取消选中事件

### 样式属性：
属性	类型	描述
titleColor	Int	标题字体颜色
titleSize	Float	标题字体大小
titleText	String	标题文字
titleScale	Float	标题字体缩放比例
iconStaticRes	Int	静态图标
iconStaticBitmap	Bitmap	静态图标(如果不为空,优先使用)
iconSvgaRes	String	动态svga动画
overSvgaRes	String	过度svga动画
backgroundColor	Int	背景颜色
isShowSvga	Boolean	是否显示iconSvgaRes,注意设置了为true后,iconSvgaRes会无限循环显示

> 每个tab都有两个tabStyle属性,分别用于选中状态和未选中状态

### api使用

```kotlin

var bottomNavigationBar = findViewById<YBottomNavigationBar>(R.id.bottomNavigaitonBar)
//设置选中和未选中的样式
var activeStyle = TabStyle().titleColor(Color.RED)
var inActiveStyle = TabStyle().titleColor(Color.BLACK)
    
//准备5个tab,分别设置选中和未选中的样式
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
   

//**设置角标(带文字)
tab1.showBadge("12")
//**设置角标(红点)
tab2.showBadge()

//初始化底部tab
bottomNavigationBar?.fragmentManager(supportFragmentManager)
		?.containerId(R.id.tabcontent)
		?.listener(listener)
		?.addItem(tabHome,TabHomeFragment::class.java,HomeUtils.TAB_TAG_HOME)
		?.addItem(tabCupid,TabCupidFragment::class.java,HomeUtils.TAB_TAG_LIVE_LOVE)
		?.addItem(tabMoment,TabMomentFragment::class.java,HomeUtils.TAB_TAG_MOMENT)
		?.addItem(tabMsg,TabConversationFragment::class.java,HomeUtils.TAB_TAG_MSG)
		?.addItem(tabMe,YiduiMeFragment2::class.java,HomeUtils.TAB_TAG_ME)
		?.firstSelectPosition(HomeUtils.TAB_INDEX_HOME)
		?.initialise()
    
//tab监听事件    
var listener = object :YBottomNavigationBar.OnSelectListener{
            override fun onSelect(position: Int?) {
                //选中tab(默认会执行切换fragment)
            }

            override fun onUnSelect(position: Int?) {
                //取消选中tab
            }

            override fun onReSelect(position: Int?) {
                //重复选中tab(默认不会触发切换fragment)
            }
}

```