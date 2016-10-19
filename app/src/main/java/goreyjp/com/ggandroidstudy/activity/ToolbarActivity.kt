package goreyjp.com.ggandroidstudy.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.extesions.gg_showLeftBarButton

// Toolbar 和 statusbar自定义 参考: http://www.jianshu.com/p/527dbe07fd8f
class ToolbarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)

        val toolbar = findViewById(R.id.tb) as Toolbar
        setSupportActionBar(toolbar)

        gg_showLeftBarButton()
//        toolbar.setNavigationIcon(R.mipmap.icon) // 这个是Toobar全部都被这个替代

        toolbar.setLogo(R.drawable.ic_android)
        toolbar.title = "大标题"
        toolbar.subtitle = "小标题"
        
    }
}
