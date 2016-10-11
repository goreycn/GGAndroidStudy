package goreyjp.com.ggandroidstudy.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.extesions.gg_showLeftBarButton


open class BaseActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        initToolbar()
    }

    fun initToolbar(){

        val toolbar = findViewById(R.id.toolbar)
        if (toolbar != null){
            setSupportActionBar(toolbar as android.support.v7.widget.Toolbar)
            gg_showLeftBarButton()
        }

    }
}