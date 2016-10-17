package goreyjp.com.ggandroidstudy.activity

import android.os.Bundle
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.activity.retrofit.GankApiService
import goreyjp.com.ggandroidstudy.activity.retrofit.MeiziInfo
import goreyjp.com.ggandroidstudy.extesions.logD
import kotlinx.android.synthetic.main.activity_retrofit.*
import org.jetbrains.anko.onClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RetrofitActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        btn_login.onClick {

            val retrofit = Retrofit.Builder().baseUrl(GankApiService.baseUrl).build()
            val apiService = retrofit.create(GankApiService::class.java)
            val call = apiService.listMeizi(1)
            call.enqueue(object: Callback<MeiziInfo?> {
                override fun onResponse(call: Call<MeiziInfo?>?, response: Response<MeiziInfo?>?) {
                    if (response?.isSuccessful!!){
                        response?.body().toString().logD()
                    }
                    else {
                        "error".logD()
                    }
                }

                override fun onFailure(call: Call<MeiziInfo?>?, t: Throwable?) {
                   "failer".logD()
                }
            })
        }
    }
}
