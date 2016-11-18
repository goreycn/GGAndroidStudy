package goreyjp.com.ggandroidstudy.activity.retrofit2

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class GGCallback<T>: Callback<BaseResultBean<T>> {


    override fun onResponse(call: Call<BaseResultBean<T>>, response: Response<BaseResultBean<T>>) {
        val body = response.body()
        if (body.code == 2000) {
            onSuccess(body.data!!)
//            okBlock(body.data!!)
        } else {
            onFailed(body.message)
        }
    }

    override fun onFailure(call: Call<BaseResultBean<T>>, t: Throwable) {
//        onFailed("系统异常")
    }

    abstract fun onSuccess(obj: T)


    fun onFailed(obj: Any) {

    }

}
