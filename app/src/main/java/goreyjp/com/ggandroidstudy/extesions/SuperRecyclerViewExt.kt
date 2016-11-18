package goreyjp.com.ggandroidstudy.extesions

import com.malinskiy.superrecyclerview.SuperRecyclerView


// 没有更新内容时, 禁用掉 加载更多
fun SuperRecyclerView.disableAskMore(){
//    val mPtrLayout = findViewById(R.id.ptr_layout) as SwipeRefreshLayout
//    mPtrLayout.isEnabled = false

//    this.swipeToRefresh.isEnabled = false
//    isLoadingMore = false
    removeMoreListener()

}

// 重新开启加载更多
fun SuperRecyclerView.enableAskMore(){
//    val mPtrLayout = findViewById(R.id.ptr_layout) as SwipeRefreshLayout
//    mPtrLayout.isEnabled = true

//    this.swipeToRefresh.isEnabled = true

//    isLoadingMore = true
}

