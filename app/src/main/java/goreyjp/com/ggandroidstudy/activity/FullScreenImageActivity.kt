package goreyjp.com.ggandroidstudy.activity

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.facebook.binaryresource.FileBinaryResource
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory
import com.facebook.imagepipeline.core.ImagePipelineFactory
import com.facebook.imagepipeline.request.ImageRequest
import com.litesuits.common.io.FileUtils
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.extesions.gg_showLeftBarButton
import kotlinx.android.synthetic.main.activity_full_screen_image.*
import me.relex.photodraweeview.OnPhotoTapListener
import java.io.File
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
        ivMeizi.onPhotoTapListener = OnPhotoTapListener { view, fl, fr ->
            toolbarStatus = !toolbarStatus
            updateToolbarByAnimate()
        }
    }

    var toolbarStatus: Boolean = true

    /**
     * 显示/隐藏 Toolbar
     */
    fun updateToolbarByAnimate() {

        if (toolbarStatus) {
            toolbar_meizi.animate().translationY(0f).interpolator = DecelerateInterpolator(2.0f)

        } else {
            toolbar_meizi.animate().translationY(-toolbar_meizi.height.toFloat()).interpolator = AccelerateInterpolator(2.0f)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, 1, 1, "保存图片")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.home -> finish()
            1 -> saveImage()
            else -> Snackbar.make(ivMeizi, "TODO", Snackbar.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun saveImage() {

        // 保存的 目录
        val externalDir = Environment.getExternalStorageDirectory()
        val fileDir = File(externalDir, "Gao")

        if (!fileDir.exists()) {
            fileDir.mkdir()
        }

        // 从缓存里取图片
        val imgUrl = intent.getStringExtra("meizi")
        val uri = Uri.parse(imgUrl)

        // Fresco缓存Key
        val cacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(ImageRequest.fromUri(uri), applicationContext)

        // 从磁盘文件里查找
        val resouce = ImagePipelineFactory.getInstance().mainFileCache.getResource(cacheKey)

        // 转换成 文件资源格式
        val fileResource = resouce as FileBinaryResource

        // 文件名
        val file = File(fileDir, "${Date().time}.jpg")

        // 复制文件
        FileUtils.copyFile(fileResource.file, file)

        // 提示完成
        Snackbar.make(ivMeizi, "保存成功", Snackbar.LENGTH_LONG).show()
    }
}
