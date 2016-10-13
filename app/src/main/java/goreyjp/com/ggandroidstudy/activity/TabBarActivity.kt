package goreyjp.com.ggandroidstudy.activity

import android.os.Bundle
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.activity.zhihu.ZhihuNewsFrag
import kotlinx.android.synthetic.main.activity_tab_bar.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.onClick

class TabBarActivity : BaseActivity() {

    var mTabIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tab_bar)

        updateTabBarUI()

        btnLeft.onClick { updateTabBarUI(0) }
        btnCenter.onClick { updateTabBarUI(1) }
        btnRight.onClick { updateTabBarUI(2) }

        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, ZhihuNewsFrag()).commitAllowingStateLoss()
    }

    fun updateTabBarUI(newIndex: Int = 0) {
        mTabIndex = newIndex

        btnLeft.backgroundResource = R.drawable.tab_bar_normal
        btnCenter.backgroundResource = R.drawable.tab_bar_normal
        btnRight.backgroundResource = R.drawable.tab_bar_normal

        when (mTabIndex) {
            0 -> btnLeft.backgroundResource = R.drawable.tab_bar_select
            1 -> btnCenter.backgroundResource = R.drawable.tab_bar_select
            2 -> btnRight.backgroundResource = R.drawable.tab_bar_select
        }

    }
}
