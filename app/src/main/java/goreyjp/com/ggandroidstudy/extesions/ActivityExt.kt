package goreyjp.com.ggandroidstudy.extesions

import android.support.v7.app.AppCompatActivity

// 是否显示返回按钮
fun AppCompatActivity.gg_showLeftBarButton(show:Boolean = true) {
    supportActionBar?.setDisplayHomeAsUpEnabled(show)
}

fun AppCompatActivity.gg_hideTitle(hide:Boolean = true) {
    supportActionBar?.setDisplayShowTitleEnabled(!hide)
}
