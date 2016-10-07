package goreyjp.com.ggandroidstudy.base

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.malinskiy.superrecyclerview.SuperRecyclerView
import goreyjp.com.ggandroidstudy.extesions.disableAskMore
import goreyjp.com.ggandroidstudy.extesions.enableAskMore
import org.json.JSONArray
import org.json.JSONObject


interface GGBindViewHolder{
    fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder?
    fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int)
}

class GGBaseAdapter(val superRecyclerView:SuperRecyclerView, val url: String, val bindVH:GGBindViewHolder) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    // 数据源
    val items = arrayListOf<JSONObject>()

    init {

        superRecyclerView.setRefreshListener {
            loadMore(true)
        }

        superRecyclerView.setupMoreListener({ a, b, c ->
            loadMore()
        }, 1)

        loadMore(true)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        bindVH.onBindViewHolder(holder, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return bindVH.onCreateViewHolder(parent, viewType)
    }


    fun loadMore(isRefresh: Boolean = false) {

        // 当前总数
        val currItemCount = items.count()

        // 待请求的数据分页
        var pageIndex = currItemCount / 10 + 1

        if (isRefresh) {
            items.clear()
            pageIndex = 1
            superRecyclerView.enableAskMore()
        }

        val url = "${url}${pageIndex}"

        url.httpGet().responseJson { request, response, result ->
            val (d, e) = result
            if (e == null) {
                val arr = d?.obj()?.get("results") as JSONArray
                if (arr.length() > 0) {

                    for (i in 0..(arr.length() - 1)) {
                        items.add(arr.get(i) as JSONObject)
                    }

                    // 没有更多时
                    if (arr.length() < 10) {
                        superRecyclerView.disableAskMore()
                    }

                }
            }

            notifyDataSetChanged()
        }

    }
}