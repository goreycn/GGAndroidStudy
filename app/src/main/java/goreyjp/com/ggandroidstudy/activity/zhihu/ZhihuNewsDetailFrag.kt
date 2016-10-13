package goreyjp.com.ggandroidstudy.activity.zhihu

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import goreyjp.com.ggandroidstudy.R

class ZhihuNewsDetailFrag : Fragment() {

    var webview: WebView? = null

    var newsId: String? = ""

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.frag_news_detail, container, false)
        webview = v?.findViewById(R.id.webview_news) as WebView

        webview?.settings?.defaultTextEncodingName = "UTF-8"

        return v
    }

    override fun onStart() {
        super.onStart()

        val nid = newsId ?: "0"
        val newsUrl = "http://news-at.zhihu.com/api/4/news/${nid}"

        newsUrl.httpGet().responseJson { request, response, result ->
            val (d, e) = result
            if (e == null) {
                val htmlStr = d?.obj()?.getString("body")
                webview?.loadData(htmlStr, "text/html; charset=UTF-8", null)
            }
        }

    }

}