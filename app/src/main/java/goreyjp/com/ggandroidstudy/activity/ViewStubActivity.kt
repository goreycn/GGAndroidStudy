package goreyjp.com.ggandroidstudy.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewStub
import android.widget.ArrayAdapter
import android.widget.ListView
import goreyjp.com.ggandroidstudy.R
import kotlinx.android.synthetic.main.activity_view_stub.*
import kotlinx.android.synthetic.main.my_title_layout.*
import org.jetbrains.anko.onClick

class ViewStubActivity : Activity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_stub)


        title_tv.text = "StubValue"

        btnShow.onClick {

            // 通过设置StubView的可见性, 来加载 StubView的内容
            val stubView = findViewById(R.id.stub_import)

            // 如果能找到ViewStub这个元素, 说明 它还没有被inflate,
            if (stubView != null) {

                val sv = stubView as ViewStub

                sv.setOnInflateListener { stub, view ->
                    // inflate好之后,  这里的参数 view 就是 inflate里的根View
                    val listView = view as ListView
                    listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListOf("AAA", "BBB", "CCC"))

                    // 要注意的是: inflate 完成后, ViewStub会从主ViewGroup里移除. 被inflate出来的layout替换.
                    // 可以理解为 ViewStub是一个延时加载机制
                }

                sv.inflate()
            }
            else {
                // 上面已经加载过的话, 接下来, 通过 ViewStub里的inflatedId 来查找, 设置 可见性即可
                val listview = findViewById(R.id.inflated_stub_id)
                if (listview != null) {
                    listview.visibility = View.VISIBLE
                }

            }

        }


        btnHide.onClick {
            val listview = findViewById(R.id.inflated_stub_id)
            if (listview != null) {
                listview.visibility = View.GONE
            }
        }

    }
}
