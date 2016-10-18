package goreyjp.com.ggandroidstudy.activity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.litesuits.common.utils.RandomUtil
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.extesions.gg_showLeftBarButton
import kotlinx.android.synthetic.main.activity_recycle_view_divider.*
import org.jetbrains.anko.act
import org.jetbrains.anko.dip

// 参考: http://blog.csdn.net/lmj623565791/article/details/45059587
class RecycleViewDividerActivity : AppCompatActivity() {

    val arr = arrayListOf(
            "A", "B", "C", "D", "A", "B", "C", "D", "A", "B", "C", "D",
            "A", "B", "C", "D", "A", "B", "C", "D", "A", "B", "C", "D",
            "A", "B", "C", "D", "A", "B", "C", "D", "A", "B", "C", "D",
            "A", "B", "C", "D", "A", "B", "C", "D", "A", "B", "C", "D"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recycle_view_divider)
        setSupportActionBar(toolbar)
        gg_showLeftBarButton(true)



        // 布局方向
//        recycle_view_divider.layoutManager = LinearLayoutManager(this)
        // 一行3个单元的 Grid布局
//        recycle_view_divider.layoutManager = GridLayoutManager(this, 3)

        recycle_view_divider.layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)


        // adapter
        recycle_view_divider.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

                // 为了显示出瀑布流效果, 这里随机设置一下ItemView的高度
                val v = holder?.itemView
                val param = v?.layoutParams
                param?.height = dip(RandomUtil.getRandom(100, 150))
                v?.layoutParams = param


                val lb = holder?.itemView?.findViewById(R.id.lbTitle) as TextView
                lb.text = arr[position]

                val iv = holder?.itemView?.findViewById(R.id.ivListview) as ImageView

                if (Build.VERSION.SDK_INT > 20){
                     iv.animate().translationZ(100.toFloat())
                }

            }

            override fun getItemCount(): Int {
                return arr.count()
            }

            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
                val v = act.layoutInflater.inflate(R.layout.cell_list_view, parent, false)
                return GGViewHolder(v)
            }

        }

        // 指定默认动画
        recycle_view_divider.itemAnimator = DefaultItemAnimator()

        recycle_view_divider.addItemDecoration(object: RecyclerView.ItemDecoration() {

        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
            R.id.menu_android -> {
                arr.add(5, "New Element")
                recycle_view_divider.adapter.notifyItemInserted(5)
            }
            R.id.menu_data -> {
                arr.removeAt(5)
                recycle_view_divider.adapter.notifyItemRemoved(5)
            }
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }


    class GGViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    }
}


