package goreyjp.com.ggandroidstudy.activity.rxjava

import android.os.Bundle
import android.widget.ArrayAdapter
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.activity.BaseActivity
import goreyjp.com.ggandroidstudy.extesions.logD
import kotlinx.android.synthetic.main.activity_rx_java.*
import org.jetbrains.anko.onItemClick
import rx.Observable

class RxJavaActivity : BaseActivity() {

    var menus = arrayListOf(
            "Create"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)

        lv_rx_java.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, menus)

        lv_rx_java.onItemClick { adapterView, view, i, l ->
            when (i) {
                0 -> onClickCreate()
                else -> "nothing".logD()
            }
        }
    }

    // 使用调度
    private fun onClickCreate() {
        Observable.create(Observable.OnSubscribe<Int> {

        })


            
        
    }
}
