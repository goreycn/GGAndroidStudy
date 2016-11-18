package goreyjp.com.ggandroidstudy.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.activity.retrofit2.API
import goreyjp.com.ggandroidstudy.activity.retrofit2.GGCallback
import goreyjp.com.ggandroidstudy.bean.MeiziBean
import goreyjp.com.ggandroidstudy.extesions.disableAskMore
import goreyjp.com.ggandroidstudy.extesions.enableAskMore
import kotlinx.android.synthetic.main.activity_retrofit2.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.anko.act
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit2Activity : Activity() {

    var client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    val retro = Retrofit.Builder()
            .client(client)
            .baseUrl("http://192.168.1.103:8080/lequ/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val url = retro.create(goreyjp.com.ggandroidstudy.activity.retrofit2.RetroURL::class.java)

    val items = arrayListOf<MeiziBean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit2)

        lv_retro2.recyclerView.layoutManager = LinearLayoutManager(this)

        lv_retro2.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
                val entity = items[position]
                val v = holder?.itemView
                val lbTitle = v?.findViewById(R.id.lbTitle) as TextView
                lbTitle.text = entity.title
            }

            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
                val v = act.layoutInflater.inflate(R.layout.cell_list_view, parent, false)
                return RecycleViewDividerActivity.GGViewHolder(v)
            }

            override fun getItemCount(): Int {
                return items.count()
            }
        }

        lv_retro2.setRefreshListener {
            loadMore(true)

            lv_retro2.setupMoreListener({ a, b, c ->
                loadMore()
            }, 1)
        }

        lv_retro2.setupMoreListener({ a, b, c ->
            loadMore()
        }, 1)

        loadMore(true)
    }

    fun loadMore(refresh: Boolean = false) {

        var page = items.count() / 10 + 1

        if (refresh) {
            items.clear()
            lv_retro2.enableAskMore()
            page = 1
        }

        val call = url.getMeizi("lequtoken", 10, page)



        API.url().getMeizi("lequ", 10, page).enqueue(object: GGCallback<MutableList<MeiziBean>?>() {
            override fun onSuccess(ll: MutableList<MeiziBean>?) {
                ll.let {
                    items.addAll(ll!!)
                    if (items.count() >= 30 || ll.count() < 10){
                        lv_retro2.disableAskMore()
                    }
                }

                lv_retro2.adapter.notifyDataSetChanged()
            }
        })


    }

}
