package goreyjp.com.ggandroidstudy.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import goreyjp.com.ggandroidstudy.R
import kotlinx.android.synthetic.main.activity_drawable.*
import org.jetbrains.anko.support.v4.act

class LayoutAnimFrag: Fragment(){


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.layout_anim_list_view, container, false)
    }


    override fun onStart() {
        super.onStart()

        val arr = arrayListOf(
                "HelloStart",
                "Hello",
                "Hello",
                "Hello",
                "Hello",
                "Hello",
                "Hello",
                "Hello",
                "Hello",
                "Hello",
                "Hello",
                "Hello",
                "Hello",
                "Hello",
                "Hello",
                "Hello",
                "HelloEnd"
        )

        listview.adapter = ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, arr)
    }

}
