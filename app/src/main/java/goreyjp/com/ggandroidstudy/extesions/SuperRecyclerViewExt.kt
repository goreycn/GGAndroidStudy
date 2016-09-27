package goreyjp.com.ggandroidstudy.extesions

import android.support.v4.widget.SwipeRefreshLayout
import com.malinskiy.superrecyclerview.R
import com.malinskiy.superrecyclerview.SuperRecyclerView


// 没有更新内容时, 禁用掉 加载更多
fun SuperRecyclerView.disableAskMore(){
    val mPtrLayout = findViewById(R.id.ptr_layout) as SwipeRefreshLayout
    mPtrLayout.isEnabled = false
}

// 重新开启加载更多
fun SuperRecyclerView.enableAskMore(){
    val mPtrLayout = findViewById(R.id.ptr_layout) as SwipeRefreshLayout
    mPtrLayout.isEnabled = true
}

