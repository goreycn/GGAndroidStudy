package goreyjp.com.ggandroidstudy

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // 启用 Toolbar
        setSupportActionBar(toolbar)

        // TabLayout 标签
        tabLayout.addTab(tabLayout.newTab().setText("One"))
        tabLayout.addTab(tabLayout.newTab().setText("Two"))
        tabLayout.addTab(tabLayout.newTab().setText("Three"))



        // 侧滑菜单响应
        navigationView.setNavigationItemSelectedListener {
            menu ->
            when (menu.itemId) {
                R.id.menu_01 -> onClickHome()
                R.id.menu_02 -> onClickBlog()
                R.id.menu_03 -> onClickAbout()
                else -> true
            }
        }
    }


    fun onClickHome(): Boolean {
        Snackbar.make(navigationView, "Home", Snackbar.LENGTH_INDEFINITE)
                .setAction("关闭"){
                    activity_main.closeDrawers()
                }
                .show()
        return true
    }

    fun onClickBlog(): Boolean {
        Snackbar.make(navigationView, "Blog", Snackbar.LENGTH_SHORT).show()
        return true
    }

    fun onClickAbout(): Boolean {
        Snackbar.make(navigationView, "About", Snackbar.LENGTH_LONG).show()
        return true
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.drawer_menu, menu)
        return true
    }

}
