package goreyjp.com.ggandroidstudy.activity.zhihu

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
import goreyjp.com.ggandroidstudy.GGViewHolder
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.base.GGBaseAdapter
import goreyjp.com.ggandroidstudy.base.GGBindViewHolder
import kotlinx.android.synthetic.main.frag_recycler_view.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.act
import org.json.JSONObject

// 知乎日报API: https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90
class ZhihuNewsFrag : Fragment() {

    var adapter: GGBaseAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.frag_recycler_view, container, false)
    }


    override fun onStart() {
        super.onStart()

        frag_recycle_view.recyclerView.layoutManager = LinearLayoutManager(act)

        adapter = GGBaseAdapter(frag_recycle_view, "http://news-at.zhihu.com/api/4/news/latest", object : GGBindViewHolder {
            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
                val v = act.layoutInflater.inflate(R.layout.cell_zhihu_news, parent, false)

                return GGViewHolder(v)
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {


                val iv = holder?.itemView?.findViewById(R.id.ivZhihu) as SimpleDraweeView
                val lb = holder?.itemView?.findViewById(R.id.lbZhihu) as TextView

                val info = adapter?.items?.get(position) as JSONObject

                val img = info.getJSONArray("images")[0] as String
                iv.setImageURI(Uri.parse(img))
                lb.text = info.getString("title")

                holder?.itemView?.onClick {
                    val newsId = info.getString("id")

                    //知乎News详情:  http://news-at.zhihu.com/api/4/news/8819656

                    val newsDetail = ZhihuNewsDetailFrag()
                    newsDetail.newsId = newsId

                    val transcation = activity.supportFragmentManager.beginTransaction()
                    transcation.setCustomAnimations(R.anim.view_fly_in, R.anim.view_fly_out)
                    transcation.add(R.id.newsDetail, newsDetail)
                    transcation.commitNowAllowingStateLoss()

                }

            }
        }, false)

        frag_recycle_view.adapter = adapter

    }
}