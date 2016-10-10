package goreyjp.com.ggandroidstudy.activity

import android.os.Bundle
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.extesions.gg_showLeftBarButton

class DataBindingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_data_binding)

//        val binding = DataBindingUtil.setContentView<ActivityDataBindingBinding>(act, R.layout.activity_data_binding)
//        binding.user = DataUser("Grubby", 38)

        val toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar as android.support.v7.widget.Toolbar)
        gg_showLeftBarButton()


    }
}
