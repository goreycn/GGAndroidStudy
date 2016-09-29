package goreyjp.com.ggandroidstudy.activity

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.extesions.gg_showLeftBarButton
import kotlinx.android.synthetic.main.activity_full_screen_image.*
import java.io.File
import java.io.FileOutputStream
import java.util.*

class FullScreenImageActivity() : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_full_screen_image)

        setSupportActionBar(toolbar_meizi)

        gg_showLeftBarButton()

        title = ""

        // 加载
        val imgUrl = intent.getStringExtra("meizi")
        val uri = Uri.parse(imgUrl)
        ivMeizi.setPhotoUri(uri)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0,1,1,"保存图片")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.home -> finish()
            1 -> saveImage()
            else -> Snackbar.make(ivMeizi, "TODO", Snackbar.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun saveImage(){

        // 保存的 目录
        val externalDir = Environment.getExternalStorageDirectory()
        val fileDir = File(externalDir, "Gao")

        if (!fileDir.exists()){
            fileDir.mkdir()
        }

        // 从缓存里取图片
        val imgUrl = intent.getStringExtra("meizi")
        val uri = Uri.parse(imgUrl)

        val dawingCache = ivMeizi.drawingCache
        if (dawingCache == null){
            return
        }

        // 文件名
        val file = File(fileDir, "${Date().time}.jpg")
        val fos = FileOutputStream(file)
        dawingCache.compress(Bitmap.CompressFormat.JPEG, 100, fos)

        Snackbar.make(currentFocus, "保存成功", Snackbar.LENGTH_LONG).show()

    }


}
