package goreyjp.com.ggandroidstudy.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.activity.WebViewActivity
import goreyjp.com.ggandroidstudy.common.AndroidUrl
import goreyjp.com.ggandroidstudy.extesions.disableAskMore
import goreyjp.com.ggandroidstudy.extesions.enableAskMore
import kotlinx.android.synthetic.main.frag_super_recycle_view.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.intentFor
import org.json.JSONArray
import org.json.JSONObject


class AndroidFrag() : Fragment() {

    // 数据源
    var androidItems = arrayListOf<JSONObject>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.frag_super_recycle_view, container, false)
    }


    override fun onStart() {
        super.onStart()

        superRecyclerView.setLayoutManager(LinearLayoutManager(act))

        superRecyclerView.setRefreshListener {
            loadMore(true)
        }

        superRecyclerView.setupMoreListener({ a, b, c ->
            loadMore()
        }, 1)

        superRecyclerView.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
                val view = act.layoutInflater.inflate(R.layout.cell_android, parent, false)
                return AndroidViewHolder(view)
            }

            override fun getItemCount(): Int {
                return androidItems.count()
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

                val info = androidItems[position]

                val mv = holder as AndroidViewHolder

                mv.lbTitle.text = info.getString("desc")
                mv.lbContent.text = Html.fromHtml(info.getString("readability"))
                mv.itemView.isClickable = true
                mv.itemView.onClick {
                    startActivity(intentFor<WebViewActivity>().putExtra("url", info.getString("url")))
                }
            }
        }

        loadMore()
    }


    fun loadMore(isRefresh: Boolean = false) {

        // 当前总数
        val currItemCount = superRecyclerView.adapter.itemCount

        // 待请求的数据分页
        var pageIndex = currItemCount / 10 + 1

        if (isRefresh) {
            androidItems.clear()
            pageIndex = 1
            superRecyclerView.enableAskMore()
        }

        val url = "${AndroidUrl}${pageIndex}"

        url.httpGet().responseJson { request, response, result ->
            val (d, e) = result
            if (e == null) {
                val arr = d?.obj()?.get("results") as JSONArray
                if (arr.length() > 0) {

                    for (i in 0..(arr.length() - 1)) {
                        androidItems.add(arr.get(i) as JSONObject)
                    }

                    // 没有更多时
                    if (arr.length() < 10) {
                        superRecyclerView.disableAskMore()
                    }

                }
            }

            superRecyclerView.adapter.notifyDataSetChanged()
        }

    }
}


class AndroidViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var lbTitle: TextView
    var lbContent: TextView

    init {
        lbTitle = view.findViewById(R.id.lbTitle) as TextView
        lbContent = view.findViewById(R.id.lbContent) as TextView
    }
}