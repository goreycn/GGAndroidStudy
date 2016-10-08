package goreyjp.com.ggandroidstudy.dialogFragment

import android.app.DialogFragment
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.extesions.slideToUp

class BottomDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater?.inflate(R.layout.frag_bottom, container, false) as View
        AnimationUtils().slideToUp(v)
        return v
    }

    override fun onStart() {
        super.onStart()

        val window = dialog.window

        val params = window.attributes
        params.gravity = Gravity.BOTTOM
        params.width = WindowManager.LayoutParams.MATCH_PARENT

        window.attributes = params

        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}