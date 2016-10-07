package goreyjp.com.ggandroidstudy.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.extesions.gg_showLeftBarButton
import goreyjp.com.ggandroidstudy.fragment.*
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_view_pager)

        setSupportActionBar(toolbar_names)

        gg_showLeftBarButton()

        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar_names.title = "Gank.io"

        viewPager.adapter = object: FragmentPagerAdapter(supportFragmentManager) {

            override fun getItem(position: Int): Fragment {
                when (position) {
                    0 -> return GankFrag()
                    1 -> return MeiziFrag()
                    2 -> return AndroidFrag()
                    3 -> return IOSFrag()
                    else -> return TitlesFragment()
                }
            }

            override fun getCount(): Int {
                return 4
            }

            override fun getPageTitle(position: Int): CharSequence {
                when (position) {
                    0 -> return "干货"
                    1 -> return "福利"
                    2 -> return "Android"
                    3 -> return "iOS"
                    else -> return ""
                }
            }
        }


        tabLayout.setupWithViewPager(viewPager)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
