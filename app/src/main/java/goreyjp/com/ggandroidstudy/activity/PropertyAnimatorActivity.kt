package goreyjp.com.ggandroidstudy.activity

import android.animation.*
import android.os.Bundle
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.extesions.logD
import kotlinx.android.synthetic.main.activity_property_animator.*
import org.jetbrains.anko.act
import org.jetbrains.anko.dip
import org.jetbrains.anko.onClick

class PropertyAnimatorActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_animator)

        btnTest.onClick {
            val ani = ObjectAnimator.ofFloat(ivDemo2, "alpha", 0f, 1f)
            ani.duration = 1000
            ani.start()
        }

        btnRotate2.onClick {
            val ani = ObjectAnimator.ofFloat(ivDemo2, "rotation", 0f, 360f)
            ani.duration = 500
            ani.start()
        }

        btnMove.onClick {
            val currX = ivDemo2.translationX
            val ani = ObjectAnimator.ofFloat(ivDemo2, "translationX", currX, currX - dip(100), currX)
            ani.duration = 1000
            ani.start()
        }

        btnScale2.onClick {
            val ani = ObjectAnimator.ofFloat(ivDemo2, "scaleX", 0.5f, 2f, 1f)
            ani.duration = 500
            ani.start()
        }

        btnAnimSet.onClick {
            val currX = ivDemo2.translationX

            val aniShake = ObjectAnimator.ofFloat(ivDemo2, "translationX", currX, currX - dip(50), currX + dip(50), currX)
            val rotate = ObjectAnimator.ofFloat(ivDemo2, "rotation", 0f, 360f)
            var alpha = ObjectAnimator.ofFloat(ivDemo2, "alpha", 1f, 0.5f, 1f)

            val aniSet = AnimatorSet()
            aniSet.play(rotate).with(alpha).before(aniShake)
            aniSet.duration = 1000

            aniSet.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    super.onAnimationStart(animation)
                    "Start".logD()
                }

                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    "End".logD()
                }
            })

            aniSet.start()
        }


        btnLoadXml2.onClick {
            val ani = AnimatorInflater.loadAnimator(act, R.animator.shake)
            ani.setTarget(window.decorView)
            ani.start()
        }

    }

    fun testValueAnimator() {
        val ani = ValueAnimator.ofInt(1, 100)
        ani.duration = 1000
        ani.addUpdateListener {
            val v = ani.animatedValue
            v.toString().logD()
        }
        ani.start()
    }
}
