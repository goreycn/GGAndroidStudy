package goreyjp.com.ggandroidstudy.activity

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.extesions.gg_showLeftBarButton
import kotlinx.android.synthetic.main.activity_full_screen_image.*

class FullScreenImageActivity() : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_full_screen_image)

        setSupportActionBar(toolbar_meizi)

        gg_showLeftBarButton()


        // 加载
        val imgUrl = intent.getStringExtra("meizi")
        val uri = Uri.parse(imgUrl)
        ivMeizi.setImageURI(uri)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0,1,1,"保存图片")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.home -> finish()
            1 -> Snackbar.make(ivMeizi, "TODO: 保存", Snackbar.LENGTH_SHORT).show()
            else -> Snackbar.make(ivMeizi, "TODO", Snackbar.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}
