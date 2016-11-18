package goreyjp.com.ggandroidstudy.activity.retrofit2

/**
 * Created by goreyjp on 16/11/18.
 */

class BaseResultBean<T> {

    var code: Int = 0
    var message: String = ""
    var data: T? = null

}