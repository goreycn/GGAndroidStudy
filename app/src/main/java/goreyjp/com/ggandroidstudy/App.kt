package goreyjp.com.ggandroidstudy

import android.app.Application
import cn.jpush.android.api.JPushInterface
import com.facebook.drawee.backends.pipeline.Fresco
import goreyjp.com.ggandroidstudy.extesions.logD

class App() : Application() {

    override fun onCreate() {
        super.onCreate()
        // Facebook 图片加载库初始化 : http://fresco-cn.org/docs/getting-started.html
        Fresco.initialize(this)

        // JPUSH Client
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)

        val registrationID = JPushInterface.getRegistrationID(this)
        registrationID.logD()
    }
}