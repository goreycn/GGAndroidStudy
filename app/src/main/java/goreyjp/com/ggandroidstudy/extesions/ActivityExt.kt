package goreyjp.com.ggandroidstudy.extesions

import android.support.v7.app.AppCompatActivity

// 是否显示返回按钮
fun AppCompatActivity.gg_showLeftBarButton(show:Boolean = true) {
    supportActionBar?.setDisplayShowHomeEnabled(show)
}
