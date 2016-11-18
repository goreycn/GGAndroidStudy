package goreyjp.com.ggandroidstudy.activity.retrofit2

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {

    fun url(): RetroURL {

        val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

        val retro = Retrofit.Builder()
                .client(client)
                .baseUrl("http://192.168.1.103:8080/lequ/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val url = retro.create(goreyjp.com.ggandroidstudy.activity.retrofit2.RetroURL::class.java)

        return url
    }

}