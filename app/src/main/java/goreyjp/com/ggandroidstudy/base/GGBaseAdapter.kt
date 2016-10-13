package goreyjp.com.ggandroidstudy.base

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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

class GGBaseAdapter(val superRecyclerView:SuperRecyclerView, val url: String, val bindVH:GGBindViewHolder, val hasMorePage:Boolean = true) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    // 数据源
    val items = arrayListOf<JSONObject>()


    init {

        superRecyclerView.setRefreshListener {
            loadMore(true)
        }

        if (hasMorePage) {
            superRecyclerView.setupMoreListener({ a, b, c ->
            }, 1)
        }

        superRecyclerView.recyclerView.addItemDecoration(object: RecyclerView.ItemDecoration() {
            override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {

                for (i in 0..(parent?.childCount as Int)){

                    val cell = parent?.getChildAt(i)
                    if (cell != null ){

                        val drawable = ColorDrawable(Color.LTGRAY)

                        val right = cell?.right as Int
                        val bottom = cell?.bottom as Int

                        drawable.setBounds(10, bottom - 1, right - 10, bottom)
                        drawable.draw(c)
                    }
                }
            }
        })

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

        var reqUrl = "${url}"
        if (hasMorePage){
            reqUrl = "${url}${pageIndex}"
        }


        reqUrl.httpGet().responseJson { request, response, result ->
            val (d, e) = result
            if (e == null) {

                var arr = JSONArray()

                if (d?.obj()?.has("results")!!){
                    arr = d?.obj()?.get("results") as JSONArray
                }
                else if (d?.obj()?.has("stories")!!) {
                    arr = d?.obj()?.get("stories") as JSONArray
                }

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