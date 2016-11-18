package goreyjp.com.ggandroidstudy.jpush

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cn.jpush.android.api.JPushInterface
import goreyjp.com.ggandroidstudy.extesions.logD

class JPushReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val bundle = intent?.extras
        intent?.action?.logD()

        if (JPushInterface.ACTION_REGISTRATION_ID == intent?.action) {
            "注册成功".logD()
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED == intent?.action) {
            "收到推送消息".logD()
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
            val title = bundle?.getString(JPushInterface.EXTRA_TITLE)
            val message = bundle?.getString(JPushInterface.EXTRA_MESSAGE)

            title?.logD()
            message?.logD()

        } else {
            "未知事件".logD()
        }
    }

}