package goreyjp.com.ggandroidstudy.activity

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.*
import com.litesuits.common.utils.RandomUtil
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.fragment.LayoutAnimFrag
import kotlinx.android.synthetic.main.activity_animate.*
import org.jetbrains.anko.act
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.dip
import org.jetbrains.anko.onClick

class AnimateActivity : BaseActivity() {

    // 懒加载
    val listFrag: LayoutAnimFrag by lazy {
        LayoutAnimFrag()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_animate)

        title = "View 动画"


        // View 动画: 动画结束后, View的属性实际上没有变. 所以这里用变量, 来记录动画的值
        var curAlpha = ivDemo.alpha

        btnAlpha.onClick {

            val ani = AlphaAnimation(curAlpha, 1f - curAlpha)
            curAlpha = 1f - curAlpha

            ani.duration = 300
            ani.fillAfter = true // 结束后, 保持图片属性
            ivDemo.startAnimation(ani)
        }


        //  当前角度
        var currAngle = 0f
        btnRotate.onClick {
            // 旋转 180度.  后两个参数, 图片旋转中心点位置.  注意: 是绝对长度
            // layout里是 100dp, 所以这里中心 取 50dp
            val ani = RotateAnimation(currAngle, currAngle + 60f, dip(50).toFloat(), dip(50).toFloat())
            currAngle += 60f
            ani.duration = 300
            ani.fillAfter = true
            ivDemo.startAnimation(ani)
        }


        var currScale = 1.0f
        btnScale.onClick {
            val newScale = RandomUtil.getRandom(3, 10).toFloat() / 10f
            val ani = ScaleAnimation(currScale, newScale, currScale, newScale)
            currScale = newScale
            ani.duration = 300
            ani.fillAfter = true
            ivDemo.startAnimation(ani)
        }

        btnTranslate.onClick {
            val ani = TranslateAnimation(0.1f, 100f, 0.1f, 100f)
            ani.duration = 300
            ani.fillAfter = true
            ivDemo.startAnimation(ani)
        }


        btnLoadXml.onClick {
            val ani = AnimationUtils.loadAnimation(act, R.anim.anim)
            ivDemo.startAnimation(ani)
        }

        btnXmlFrame.onClick {
            vDemo.backgroundResource = R.drawable.frame_ani
            val ani = vDemo.background as AnimationDrawable
            if (ani.isRunning) {
                ani.stop()
            } else {
                ani.start()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.addSubMenu(0, 0, 0, "加载Fragment")
        menu?.addSubMenu(0, 1, 1, "去掉Fragment")
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            0 -> {
                // 加载Fragment 进来
                val mngTras = supportFragmentManager.beginTransaction()
                mngTras.setCustomAnimations(R.anim.view_fly_in, R.anim.view_fly_out)
                mngTras.replace(R.id.frag_container, listFrag)
                mngTras.commitAllowingStateLoss()
            }
            1 -> {
                val mngTras = supportFragmentManager.beginTransaction()
                mngTras.setCustomAnimations(R.anim.view_fly_in, R.anim.view_fly_out)
                mngTras.remove(listFrag)
                mngTras.commitAllowingStateLoss()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
