package goreyjp.com.ggandroidstudy.activity

import android.os.Bundle
import goreyjp.com.ggandroidstudy.R
import goreyjp.com.ggandroidstudy.activity.retrofit.GitHubSimpleService
import kotlinx.android.synthetic.main.activity_retrofit.*
import org.jetbrains.anko.onClick
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers

class RetrofitActivity : BaseActivity() {

    val client = Retrofit.Builder().baseUrl(GitHubSimpleService.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        btn_login.onClick {
            client.create(GitHubSimpleService.GitHub::class.java)
                    .contributors("square", "retrofit")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(Action1 {
                        it.forEach {
                            lb_output.append(it.login + "\n")
                        }
                    })

        }
    }


}
