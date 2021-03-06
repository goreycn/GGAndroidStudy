package goreyjp.com.ggandroidstudy

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import goreyjp.com.ggandroidstudy.activity.*
import goreyjp.com.ggandroidstudy.activity.rxjava.RxJavaActivity
import goreyjp.com.ggandroidstudy.dialogFragment.BottomDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // 启用 Toolbar
        setSupportActionBar(toolbar)

        toolbarLayout.title = "Study Memo"


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

        // 显示返回
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val menuArr = arrayListOf(
                "ViewPager",
                "BottomDialog",
                "ListView",
                "ViewStub",
                "RecycleViewDivider",
                "Drawable",
                "DataBinding",
                "ViewAnimation",
                "PropertyAnimator",
                "Fragment",
                "TabBarZhihu",
                "RxJava",
                "Retrofit",
                "SystemInfo",
                "Toolbar",
                "OSSFileDemo",
                "Retrofit2"
                )

        // 主体内容
        listview.layoutManager = LinearLayoutManager(this)
        listview.adapter = object: RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
                val vh = holder as GGViewHolder
                val lbTitle = vh.itemView.findViewById(R.id.textView) as TextView
                lbTitle.text = menuArr[position]
                vh.itemView.onClick {

                    when (position) {
                        0 -> startActivity(intentFor<ViewPagerActivity>())
                        1 -> {
                            val bottomDialog = BottomDialog()
                            bottomDialog.show(fragmentManager, "hello")
                        }
                        2 -> startActivity(intentFor<ListViewActivity>())
                        3 -> startActivity(intentFor<ViewStubActivity>())
                        4 -> startActivity(intentFor<RecycleViewDividerActivity>())
                        5 -> startActivity(intentFor<DrawableActivity>())
                        6 -> startActivity(intentFor<DataBindingActivity>())
                        7 -> startActivity(intentFor<AnimateActivity>())
                        8 -> startActivity(intentFor<PropertyAnimatorActivity>())
                        9 -> startActivity(intentFor<FragmentDemoActivity>())
                        10 -> startActivity(intentFor<TabBarActivity>())
                        11 -> startActivity(intentFor<RxJavaActivity>())
                        12 -> startActivity(intentFor<RetrofitActivity>())
                        13 -> startActivity(intentFor<AndroidInfoActivity>())
                        14 -> startActivity(intentFor<ToolbarActivity>())
                        15 -> testOSSFileUpload()
                        16 -> startActivity(intentFor<Retrofit2Activity>())
                        else -> toast("click menu at row ${position} ")
                    }

                }
            }

            override fun getItemCount(): Int {
                return menuArr.count()
            }

            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {

                val v = layoutInflater.inflate(R.layout.main_activity_cell, parent, false)
                return GGViewHolder(v)
            }
        }

        listview.adapter.notifyDataSetChanged()
    }

    private fun testOSSFileUpload() {
        //OSSClient.
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            activity_main.openDrawer(Gravity.START)
        }

        return super.onOptionsItemSelected(item)
    }

}

class GGViewHolder(view:View):RecyclerView.ViewHolder(view) {

}
