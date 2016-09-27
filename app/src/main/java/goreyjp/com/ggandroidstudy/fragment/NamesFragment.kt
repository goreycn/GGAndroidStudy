package goreyjp.com.ggandroidstudy.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import goreyjp.com.ggandroidstudy.R


open class NamesFragment():Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_names, container, false)
    }

    override fun onStart() {
        super.onStart()

//        listview.backgroundColor = Color.argb(255,Random().nextInt(255),Random().nextInt(255),Random().nextInt(255))

    }


}