package goreyjp.com.ggandroidstudy.activity

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ArrayAdapter
import android.widget.ListView
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.extesions.closeKeyboard
import goreyjp.com.ggandroidstudy.extesions.ggOnTouch
import goreyjp.com.ggandroidstudy.extesions.gg_showLeftBarButton
import kotlinx.android.synthetic.main.activity_drawable.*
import org.jetbrains.anko.*

class DrawableActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_drawable)

        //  配置导航条
        setSupportActionBar(findViewById(R.id.toolbar) as Toolbar?)
        gg_showLeftBarButton()
        title = "Drawable Test"

        // 给按钮加上背景
        btnDemo.backgroundResource = R.drawable.button
        tfInput.backgroundResource = R.drawable.textfield

        // 变化按钮事件
        btnTransite.backgroundResource = R.drawable.transition
        btnTransite.onClick {
            val drawable = btnTransite.backgroundDrawable as TransitionDrawable

            if (btnTransite.isChecked){
                drawable.startTransition(300)
            }
            else {
                drawable.reverseTransition(300)
            }
        }

        // 背景空白点击后, 关闭键盘
        activity_drawable.isClickable = true
        activity_drawable.onClick {
            tfInput.closeKeyboard(act)
        }

        // 默认关闭键盘
        tfInput.closeKeyboard(act)

        // 给输入框加监听
        tfInput.ggOnTouch()

        // ListView
        val listview = findViewById(R.id.listview) as ListView

        val types = arrayOf(
                "从Assets加载Bitmap",
                "从mipmap加载Bitmap",
                "从mipmap加载Drawable",
                "设置shape边框"
        )
        listview.adapter = ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, types)
        listview.onItemClick { adapterView, view, i, l ->
            when (l.toInt()) {
                0 -> loadImageFromAssets()
                1 -> loadImageFromResouce()
                2 -> loadDrawableFromResouce()
                3 -> {
                    listview.backgroundResource = R.drawable.one
                }
            }
        }
    }


    // 从Assets加载图片
    fun loadImageFromAssets() {

        val imageStream = assets.open("mm.png")
        val bmp = BitmapFactory.decodeStream(imageStream)
        ivLocal.imageBitmap = bmp
    }

    // 从Resouce加载
    fun loadImageFromResouce() {
        val img = BitmapFactory.decodeResource(resources, R.mipmap.icon)
        ivLocal.imageBitmap = img
    }

    // 加载Drawable
    private fun loadDrawableFromResouce() {
        val img = BitmapFactory.decodeResource(resources, R.mipmap.loading)
        val dd = BitmapDrawable(resources, img)
        ivLocal.background = dd
    }


}
