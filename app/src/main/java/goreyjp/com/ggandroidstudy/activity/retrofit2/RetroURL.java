package goreyjp.com.ggandroidstudy.activity.retrofit2;

import java.util.List;

import goreyjp.com.ggandroidstudy.bean.MeiziBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 *
 * Created by goreyjp on 16/11/18.
 */

public interface RetroURL {


    @GET("race/list")
    Call<BaseResultBean<List<MeiziBean>>> getMeizi(
            @Header("token") String token,
            @Query("page_size") int page_size,
            @Query("page") int page);

//    @POST("race/list")
//    @Multipart
//    Call<BaseResultBean<List<MeiziBean>>> getMeizi(
//            @Header("token") String token,
//            @Part("page_size") int page_size,
//            @Part("page") int page);


}
