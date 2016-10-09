package goreyjp.com.ggandroidstudy.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.extesions.gg_showLeftBarButton
import kotlinx.android.synthetic.main.activity_list_view.*
import org.jetbrains.anko.onItemClick
import org.jetbrains.anko.toast

class ListViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list_view)

        setSupportActionBar(toolbar_list_view)
        gg_showLeftBarButton(true)

        val arr = arrayOf("a113", "b22", "c333", "d444", "p", "p", "p", "p", "p")

        listview.adapter = object : BaseAdapter() {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

                val holder: ViewHolder

                if (convertView == null) {

                    val v = layoutInflater.inflate(R.layout.cell_list_view, parent, false)
                    holder = ViewHolder(v.findViewById(R.id.ivListview) as ImageView,
                            v.findViewById(R.id.lbTitle) as TextView)
                    v.tag = holder

                    holder.title.text = arr[position]
                    return v

                } else {
                    holder = convertView.tag as ViewHolder
                    holder.title.text = arr[position]
                    return convertView
                }

            }

            override fun getItem(position: Int): Any {
                return arr[position]
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun getCount(): Int {
                return arr.count()
            }
        }


        listview.onItemClick { adapterView, view, i, l ->
            toast("${arr[i]}")
        }

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    class ViewHolder(val img: ImageView, val title: TextView) {

    }

}
