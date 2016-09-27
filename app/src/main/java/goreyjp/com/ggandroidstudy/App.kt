package goreyjp.com.ggandroidstudy

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class App():Application(){
    override fun onCreate() {
        super.onCreate()
        // Facebook 图片加载库初始化 : http://fresco-cn.org/docs/getting-started.html
        Fresco.initialize(this)
    }
}