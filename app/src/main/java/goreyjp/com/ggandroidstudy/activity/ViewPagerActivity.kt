package goreyjp.com.ggandroidstudy.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.fragment.NamesFragment
import goreyjp.com.ggandroidstudy.fragment.TitlesFragment
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_view_pager)

        setSupportActionBar(toolbar_names)


        viewPager.adapter = object: FragmentPagerAdapter(supportFragmentManager) {

            override fun getItem(position: Int): Fragment {
                if (position == 1){
                    return TitlesFragment()
                }
                else {
                    return NamesFragment()
                }
            }

            override fun getCount(): Int {
                return 3
            }

            override fun getPageTitle(position: Int): CharSequence {
                return "tab ${position}"
            }
        }


        tabLayout.setupWithViewPager(viewPager)
    }
}
