package goreyjp.com.ggandroidstudy.fragment

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.common.MeiziUrl
import goreyjp.com.ggandroidstudy.extesions.disableAskMore
import goreyjp.com.ggandroidstudy.extesions.enableAskMore
import kotlinx.android.synthetic.main.frag_super_recycle_view.*
import org.jetbrains.anko.support.v4.act
import org.json.JSONArray
import org.json.JSONObject

class MeiziFrag() : Fragment() {

    // 妹子数据源
    var meiziItems = arrayListOf<JSONObject>()


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
                val view =  act.layoutInflater.inflate(R.layout.cell_meizi, parent, false)
                return MeiziViewHolder(view)
            }

            override fun getItemCount(): Int {
                return meiziItems.count()
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

                val meizi = meiziItems[position]

                val mv = holder as MeiziViewHolder

                val uri = Uri.parse(meizi.getString("url"))
                mv.ivAvatar.setImageURI(uri)
                mv.lbName.text = meizi.getString("desc")

            }
        }

        loadMore()
    }


    fun loadMore(isRefresh:Boolean = false){

        // 当前总数
        val currItemCount = superRecyclerView.adapter.itemCount

        // 待请求的数据分页
        var pageIndex = currItemCount/10 + 1

        if (isRefresh){
            meiziItems.clear()
            pageIndex = 1
            superRecyclerView.enableAskMore()
        }

        val url = "${MeiziUrl}${pageIndex}"

        url.httpGet().responseJson { request, response, result ->
            val (d, e) = result
            if (e == null) {
                val arr = d?.obj()?.get("results") as JSONArray
                if (arr.length() > 0) {

                    for (i in 0..(arr.length() - 1)) {
                        meiziItems.add(arr.get(i) as JSONObject)
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

class MeiziViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var ivAvatar: SimpleDraweeView
    var lbName:TextView

    init {
        ivAvatar = view.findViewById(R.id.ivAvatar) as SimpleDraweeView
        lbName = view.findViewById(R.id.lbName) as TextView
    }
}