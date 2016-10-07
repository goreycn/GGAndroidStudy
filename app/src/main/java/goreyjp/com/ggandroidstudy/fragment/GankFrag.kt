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
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.base.GGBaseAdapter
import goreyjp.com.ggandroidstudy.base.GGBindViewHolder
import goreyjp.com.ggandroidstudy.common.MeiziUrl
import kotlinx.android.synthetic.main.frag_super_recycle_view.*
import org.jetbrains.anko.support.v4.act

class GankFrag() : Fragment() {

    var adapter:GGBaseAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // 返回
        return inflater?.inflate(R.layout.frag_super_recycle_view, container, false)
    }

    override fun onStart() {
        super.onStart()

        // 垂直方向
        superRecyclerView.setLayoutManager(LinearLayoutManager(act))

        adapter = GGBaseAdapter(superRecyclerView, MeiziUrl, object: GGBindViewHolder {

            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
                val v = act.layoutInflater.inflate(R.layout.cell_meizi, parent, false)
                return GankViewHolder(v)
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
                val info = adapter?.items?.get(position)
                val mv = holder as GankViewHolder
                mv.lbTitle?.text = info?.getString("desc")
                mv.ivAvatar?.setImageURI(Uri.parse(info?.getString("url")))

            }
        })

        superRecyclerView.adapter = adapter
    }
}

class GankViewHolder(view: View):RecyclerView.ViewHolder(view) {
    var ivAvatar: SimpleDraweeView? = null
    var lbTitle:TextView? = null

    init {
        ivAvatar = view.findViewById(R.id.ivAvatar) as SimpleDraweeView?
        lbTitle = view.findViewById(R.id.lbName) as TextView?
    }
}
