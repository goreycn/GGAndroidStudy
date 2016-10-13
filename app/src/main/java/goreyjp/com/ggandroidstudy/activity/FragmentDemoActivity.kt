package goreyjp.com.ggandroidstudy.activity

import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.extesions.logD

class FragmentDemoActivity : BaseActivity() {

    val fragLeft:FragLeft by lazy {
        FragLeft()
    }

    val fragRight:FragRight by lazy {
        FragRight()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_demo)


        // 取得屏幕大小
        var winSize = Point()
        windowManager.defaultDisplay.getSize(winSize)

        with(winSize){
            if (x > y){
                fragmentManager.beginTransaction().replace(R.id.frag_frame, fragLeft).commitAllowingStateLoss()
            }
            else {
                fragmentManager.beginTransaction().replace(R.id.frag_frame, fragRight).commitAllowingStateLoss()
            }
        }

    }
}

class FragLeft: android.app.Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        "Left OnCreateView".logD()
        return inflater?.inflate(R.layout.frag1, container, false)
    }

    override fun onStart() {
        super.onStart()
        "Left onStart".logD()
    }

}

class FragRight: android.app.Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        "Right OnCreateView".logD()
        return inflater?.inflate(R.layout.frag2, container, false)
    }

    override fun onStart() {
        super.onStart()
        "Right onStart".logD()
    }
}


