package goreyjp.com.ggandroidstudy.activity

import android.os.Bundle
import android.webkit.WebView
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.webView

class WebViewActivity : BaseActivity() {

    var webview:WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout {
            webview = webView {

            }
        }

        val url = intent.getStringExtra("url")
        webview?.loadUrl(url)
    }

}
