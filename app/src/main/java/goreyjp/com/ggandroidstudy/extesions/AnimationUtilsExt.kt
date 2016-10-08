package goreyjp.com.ggandroidstudy.extesions

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation

/**
 * 向上滑动动画
 */
fun AnimationUtils.slideToUp(v: View) {

    val slide = TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 1.0f,
            Animation.RELATIVE_TO_SELF, 0.0f)

    slide.duration = 400
    slide.fillAfter = true
    slide.isFillEnabled = true

    v.startAnimation(slide)

    slide.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {

        }

        override fun onAnimationEnd(animation: Animation?) {

        }

        override fun onAnimationStart(animation: Animation?) {

        }
    })
}
