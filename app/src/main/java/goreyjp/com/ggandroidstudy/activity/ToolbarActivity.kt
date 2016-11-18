package goreyjp.com.ggandroidstudy.activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.extesions.gg_showLeftBarButton
import kotlinx.android.synthetic.main.activity_toolbar.*
import org.jetbrains.anko.act
import org.jetbrains.anko.backgroundColor

// Toolbar 和 statusbar自定义 参考: http://www.jianshu.com/p/527dbe07fd8f
class ToolbarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Android 4.0 以下时, 全屏
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        else {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        setContentView(R.layout.activity_toolbar)

        mStatusBarView.backgroundColor = resources.getColor(R.color.colorPrimary, null)


        val toolbar = findViewById(R.id.tb) as Toolbar
        setSupportActionBar(toolbar)

        gg_showLeftBarButton()
//        toolbar.setNavigationIcon(R.mipmap.icon) // 这个是Toobar全部都被这个替代


        // 降暗状态栏的高度, 但是, 用户手点击一下就会自动恢复
//        val decorView = act.window.decorView
//        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILEΩ

        toolbar.setLogo(R.drawable.ic_android)
        toolbar.title = "大标题"
        toolbar.subtitle = "小标题"




    }

    val mStatusBarView:View by lazy {
        val statusBarView = View(act)
        val lp = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(act))
        activity_toolbar.addView(statusBarView, lp)
        statusBarView
    }

    /**
     * 获得状态栏高度
     */
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android")
        return context.getResources().getDimensionPixelSize(resourceId)
    }
}
