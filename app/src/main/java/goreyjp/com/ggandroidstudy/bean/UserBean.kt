package goreyjp.com.ggandroidstudy.bean

import android.databinding.ObservableField
import android.databinding.ObservableInt

class UserBean{
    var name = ObservableField<String>()
    var age = ObservableInt()
}