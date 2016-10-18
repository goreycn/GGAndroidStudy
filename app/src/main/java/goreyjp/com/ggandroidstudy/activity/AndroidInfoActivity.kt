package goreyjp.com.ggandroidstudy.activity

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import goreyjp.com.ggandroidstudy.R
import kotlinx.android.synthetic.main.activity_android_info.*
import org.jetbrains.anko.act


class AndroidInfoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_android_info)

        val list = arrayListOf(
                "主板: " + Build.BOARD,
                "Android系统定制商: " + Build.BRAND,
                "CPU指令集: " + Build.SUPPORTED_ABIS.joinToString(","),
                "设备参数: " + Build.DEVICE,
                "显示屏参数: " + Build.DISPLAY,
                "唯一编号:" + Build.FINGERPRINT,
                "硬件序列号:" + Build.SERIAL,
                "修订版本列表:" + Build.ID,
                "硬件制造商:" + Build.MANUFACTURER,
                "版本:" + Build.MODEL,
                "硬件名:" + Build.HARDWARE,
                "手机产品名:" + Build.PRODUCT,
                "描述Build标签:" + Build.TAGS,
                "类型:" + Build.TYPE,
                "当前开发代号:" + Build.VERSION.CODENAME,
                "源码控制版本号:" + Build.VERSION.INCREMENTAL,
                "版本字符串:" + Build.VERSION.RELEASE,
                "版本号:" + Build.VERSION.SDK_INT,
                "Host值:" + Build.HOST,
                "User名:" + Build.USER,
                "编译时间:" + Build.TIME,
                "OS版本: " + System.getProperty("os.version"),
                "OS名称: " + System.getProperty("os.name"),
                "OS架构: " + System.getProperty("os.arch"),
                "Dir属性: " + System.getProperty("user.dir"),
                "时区: " + System.getProperty("user.timezone"),
                "路径分隔符: " + System.getProperty("path.separator"),
                "行分隔符: " + System.getProperty("line.separator"),
                "文件分隔符: " + System.getProperty("file.separator"),
                "Java Vendor URL: " + System.getProperty("java.vendor.url"),
                "Java Class 路径: " + System.getProperty("java.class.path"),
                "Java Class 版本: " + System.getProperty("java.class.version"),
                "Java Vendor属性: " + System.getProperty("java.vendor"),
                "Java版本: " + System.getProperty("java.version"),
                "Java Home: " + System.getProperty("java.home")
        )

        lv_buid_info.adapter = ArrayAdapter(act, android.R.layout.simple_list_item_1, list)
    }
}
