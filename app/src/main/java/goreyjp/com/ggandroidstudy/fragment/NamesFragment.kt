package goreyjp.com.ggandroidstudy.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import goreyjp.com.ggandroidstudy.R
import kotlinx.android.synthetic.main.fragment_names.*
import org.jetbrains.anko.backgroundColor
import java.util.*


open class NamesFragment():Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_names, container, false)
    }

    override fun onStart() {
        super.onStart()

        listview.backgroundColor = Color.argb(255,Random().nextInt(255),Random().nextInt(255),Random().nextInt(255))


//
//        val titles = arrayListOf(
//                "1",
//                "=",
//                "{",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "addddd",
//                "ab"
//        )
//
//        listview.adapter = object: ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, titles){}

    }


}