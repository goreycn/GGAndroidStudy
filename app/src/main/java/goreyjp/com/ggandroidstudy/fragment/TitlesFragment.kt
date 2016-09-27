package goreyjp.com.ggandroidstudy.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import goreyjp.com.ggandroidstudy.GGViewHolder
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.activity.ViewPagerActivity
import kotlinx.android.synthetic.main.frag_names_two.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast

class TitlesFragment():Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.frag_names_two, container, false)
    }

    override fun onStart() {
        super.onStart()


        val menuArr = arrayListOf(
                "Test",
                "Gank.io",
                "Gank.io",
                "Gank.io",
                "Gank.io",
                "Gank.io",
                "Gank.io",
                "Gank.io",
                "Gank.io",
                "Gank.io",
                "Gank.io",
                "Gank.io",
                "Gank.io",
                "Gank"
        )

        // 主体内容
        recycleViewList.layoutManager = LinearLayoutManager(act)
        recycleViewList.adapter = object: RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
                val vh = holder as GGViewHolder
                val lbTitle = vh.itemView.findViewById(R.id.textView) as TextView
                lbTitle.text = menuArr[position]
                vh.itemView.onClick {

                    when (position) {
                        0 -> startActivity(intentFor<ViewPagerActivity>())
                        else -> toast("click menu at row ${position} ")
                    }

                }
            }

            override fun getItemCount(): Int {
                return menuArr.count()
            }

            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {

                val v = act.layoutInflater.inflate(R.layout.main_activity_cell, parent, false)
                return GGViewHolder(v)
            }
        }
    }
}